package com.example.webproject.controller2;


import com.example.webproject.dao.ProductDao;
import com.example.webproject.entity.*;
import com.example.webproject.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/cliente")
public class ClientController {

    @Autowired
    PlantasRepository plantasRepository;

    @Autowired
    PublicacionRepository publicacionRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CompraRepository compraRepository;

    @Autowired
    DetallecompraRepository detallecompraRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    ConsultaRepository consultaRepository;

    @Autowired
    ProductDao productDao;

    List<Compra> listacompra =new ArrayList<>();
    List<Detallecompra> listadetallecompra=new ArrayList<>();
    int item=0;
    double totalPagar;
    int numplantas=0;

    static int contador2 =0;


    @GetMapping("/index")
    public String indice(Model model){

        model.addAttribute("contador", contador2);
        return "cliente/index";
    }

    @GetMapping("/about")
    public String acercade(Model model){
        model.addAttribute("contador", contador2);
        return "cliente/about";
    }

    @GetMapping(value="/shop")
    public String inicio(Model model){

        model.addAttribute("productList", plantasRepository.plantas());

        model.addAttribute("contador", contador2);
        if(plantasRepository.plantas().get(0).getNombre().isEmpty()){
            System.out.println("FFFFFFFFF");
        }
        System.out.println(plantasRepository.plantas().get(0).getNombre());

        return "cliente/shop";
    }

    //Detalle por planta
    @GetMapping("/shopdetails")
    public String detallestienda(Model model,@RequestParam("id") String id){
        Optional<Plantas> opt= plantasRepository.findById(Integer.parseInt(id));
        if(opt.isPresent()) {
            List<Plantas> planta1=plantasRepository.findplants(Integer.parseInt(id));
            model.addAttribute("idplantas",planta1.get(0).getIdplantas());
            model.addAttribute("planta",planta1.get(0));
            model.addAttribute("contador", contador2);
            return "cliente/shop-details";
        }else {
            return "pag_no_encontrada";
        }


    }

    //---------------------PUBLICACIONES-----------------------------------
    @GetMapping("/publicaciones")
    public String blog(Model model){
        model.addAttribute("contador", contador2);
        List<Publicacion> publicaciones=publicacionRepository.publicaciones();
        for(Publicacion publicacion:publicaciones){
            System.out.println(publicacion.getResumen());
            System.out.println(publicacion.getTexto());
            System.out.println(publicacion.getTitulo());
        }
        model.addAttribute("publicaciones",publicaciones);

        return "cliente/blog";
    }
    //FUNCION PARA LAS IMAGENES DE LISTA DE PLANTAS
    @GetMapping("/imagen/{id}")
    public ResponseEntity<byte[]> mostrarImagenplantas(@PathVariable ("id") int id){
        Optional<Plantas> opt= plantasRepository.findById(id);
        if(opt.isPresent()){
            Plantas p= opt.get();
            System.out.println("AAAAAAAAAAAAAAAAAAAAAA");
            byte[] imagenComoBytes=p.getImagen();
            byte[] imagenComoBytes2=p.getImagen2();
            HttpHeaders httpHeaders=new HttpHeaders();
            httpHeaders.setContentType(MediaType.parseMediaType(p.getImagencontenttype()));
            return new ResponseEntity<>(
                    imagenComoBytes,
                    httpHeaders,
                    HttpStatus.OK);
        }else{
            return null;
        }
    }
    @GetMapping("/imagen2/{id}")
    public ResponseEntity<byte[]> mostrarImagen(@PathVariable("id") int id){
        Optional<Publicacion> opt= publicacionRepository.findById(id);
        if(opt.isPresent()){
            Publicacion p= opt.get();
            System.out.println("AAAAAAAAAAAAAAAAAAAAAA");
            byte[] imagenComoBytes=p.getImagenpubli();
            HttpHeaders httpHeaders=new HttpHeaders();
            httpHeaders.setContentType(MediaType.parseMediaType(p.getImagencontenttypepubli()));
            return new ResponseEntity<>(
                    imagenComoBytes,
                    httpHeaders,
                    HttpStatus.OK);
        }else{
            return null;
        }
    }


    @GetMapping("/single_post")
    public String postunico(Model model, @RequestParam("id") String id){
        System.out.println(id);
        model.addAttribute("contador", contador2);
        Optional<Publicacion> opt= publicacionRepository.findById(Integer.parseInt(id));
        if(opt.isPresent()) {
            List<Publicacion> publicacion=publicacionRepository.findpubli(Integer.parseInt(id));
            for(Publicacion publicacion1:publicacion){
                System.out.println(publicacion1.getResumen());
                System.out.println(publicacion1.getTexto());
                System.out.println(publicacion1.getTitulo());
            }
            model.addAttribute("publicacion",publicacion.get(0));
            model.addAttribute("contador", contador2);
            return "single-post";
        }else {
            return "pag_no_encontrada";
        }
    }

    //--------------------CONTACTO------------------------

    @GetMapping("/contact")
    public String contacto(@ModelAttribute("consultante") Consulta consultante, Model model){
        model.addAttribute("contador", contador2);
        return "cliente/contact";
    }

    //---------------------------------CARRITO---------------------------------------------------



    @GetMapping(value="/carrito")
    public String carrito(Model model){
        totalPagar=0.0;
        for(int i=0;i<listadetallecompra.size();i++){
            totalPagar=totalPagar+ listadetallecompra.get(i).getPreciocompra();
        }
        model.addAttribute("total",totalPagar);
        model.addAttribute("carrito",listadetallecompra);
        System.out.println("+++++++++++++++++++++");

        /*for (Compra compra:listacompra) {
            System.out.println("ID "+ compra.getIdcompra());
            System.out.println("ID PLANTA "+ compra.getPlantas().getIdplantas()+
                    "  Nombre planta "+ compra.getPlantas().getNombre()+ "  CANTIDAD:"+ compra.getNumplantas());
        }*/
        model.addAttribute("contador",listadetallecompra.size());
        return "cliente/checkout";

    }

    //añadir carrito2
    @GetMapping("/add")
    public String add2(Model model,@RequestParam("id") String id){
        Optional<Plantas> opt= plantasRepository.findById(Integer.parseInt(id));
        numplantas=0;
        int find=0;
        int findid;

        if(opt.isPresent()) {
            //obtengo la planta
            Plantas p = opt.get();
            //obtengo planta
            //detallecompra normal , primer producto
            if(listadetallecompra.size()==0){
                numplantas = numplantas + 1;
                Detallecompra detallecompra=new Detallecompra();
                //por planta un nuevo id
                item=item+1;
                detallecompra.setIddetallecompra(item);
                //cantidad de esa planta
                detallecompra.setCantidad(numplantas);
                //setea la planta
                detallecompra.setPlantas(p);
                // define el precio
                detallecompra.setPreciocompra(numplantas*p.getPrecio());
                listadetallecompra.add(detallecompra);
                model.addAttribute("productList", plantasRepository.plantas());
                contador2 =listadetallecompra.size();
                model.addAttribute("contador", contador2);
                return "shop";
            } else {
                for(Detallecompra compra:listadetallecompra){
                    if(compra.getPlantas().getIdplantas()==Integer.parseInt(id)){
                        find = 1;
                        numplantas= compra.getCantidad()+1;
                        compra.setCantidad(numplantas);
                        compra.setPreciocompra(numplantas * p.getPrecio());
                        model.addAttribute("productList", plantasRepository.plantas());
                        contador2 =listadetallecompra.size();
                        model.addAttribute("contador", contador2);
                        return "shop";
                    }
                }
                item=item+1;
                numplantas = numplantas + 1;
                Detallecompra detallecompra=new Detallecompra();
                //por planta un nuevo id
                detallecompra.setIddetallecompra(item);
                //cantidad de esa planta
                detallecompra.setCantidad(numplantas);
                //setea la planta
                detallecompra.setPlantas(p);
                // define el precio
                detallecompra.setPreciocompra(numplantas*p.getPrecio());
                listadetallecompra.add(detallecompra);
                model.addAttribute("productList", plantasRepository.plantas());
                contador2 =listadetallecompra.size();
                model.addAttribute("contador", contador2);
                return "shop";
            }
        }else {
            return "pag_no_encontrada";
        }

    }




    @GetMapping(value="/delete")
    public String del(Model model,@RequestParam("id") String id){
        //System.out.println(listacompra.size());
        /*
        for(Compra compra1:listacompra){
            System.out.println(compra1.getPlantas().getNombre());
        }*/

        if(listadetallecompra.size()==1){
            listadetallecompra.remove(0);
            contador2 =0;
            return "redirect:/cliente/shop";
        }
        System.out.println("IDD" + id);
        int id1=Integer.parseInt(id);
        int i=0;
        for (Detallecompra compra:listadetallecompra) {
            if(compra.getPlantas().getIdplantas()==Integer.parseInt(id)) {
                listadetallecompra.remove(i);
                contador2 =0;
                return "redirect:/cliente/shop";
            }
            i=i+1;
        }


        return "redirect:/cliente/shop";

    }

    //------------------------------------------------------------
    //archivo

    @GetMapping("/imagen22/{id}")
    public ResponseEntity<byte[]> mostrarImagen2(@PathVariable ("id") int id){
        Optional<Plantas> opt= plantasRepository.findById(id);
        if(opt.isPresent()){
            Plantas p= opt.get();
            System.out.println("AAAAAAAAAAAAAAAAAAAAAA");
            byte[] imagenComoBytes2=p.getImagen2();
            HttpHeaders httpHeaders=new HttpHeaders();
            httpHeaders.setContentType(MediaType.parseMediaType(p.getImagencontenttype2()));
            return new ResponseEntity<>(
                    imagenComoBytes2,
                    httpHeaders,
                    HttpStatus.OK);
        }else{
            return null;
        }
    }

    @GetMapping("/imagen3/{id}")
    public ResponseEntity<byte[]> mostrarImagen3(@PathVariable ("id") int id){
        Optional<Plantas> opt= plantasRepository.findById(id);
        if(opt.isPresent()){
            Plantas p= opt.get();
            System.out.println("AAAAAAAAAAAAAAAAAAAAAA");
            byte[] imagenComoBytes3=p.getImagen3();
            HttpHeaders httpHeaders=new HttpHeaders();
            httpHeaders.setContentType(MediaType.parseMediaType(p.getImagencontenttype3()));
            return new ResponseEntity<>(
                    imagenComoBytes3,
                    httpHeaders,
                    HttpStatus.OK);
        }else{
            return null;
        }
    }
    @GetMapping("/perfil")
    public String perfil_cliente(Model model, Principal principal, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("user");

        List<Compra> compras = compraRepository.findComprasByUsuarioId(usuario.getIdusuario());

        model.addAttribute("compras", compras);

        return "cliente/perfil";
    }



    @GetMapping("/guardar_compra")
    public String guardarcompra(){
        Optional<Usuario> optionalUsuario=usuarioRepository.findById(2);
        Usuario usuario=optionalUsuario.get();
        //1 creo compra
        Compra compra=new Compra();
        List<Compra> opt= compraRepository.findAll();

        List<Detallecompra> optD= detallecompraRepository.findAll();
        //int idcompra=opt.size()+1;
        int totalplantas=0;
        double monto=0;
        compra.setEstado("proceso");
        //compra.setIdcompra(idcompra);
        compra.setUsuario(usuario);
        for(Detallecompra detallecompra:listadetallecompra){
            totalplantas=totalplantas+detallecompra.getCantidad();
            monto=monto+detallecompra.getPreciocompra();
        }
        compra.setNumplantas(totalplantas);
        compra.setMonto(monto);
        compraRepository.save(compra);
        int iddetalle=0;
        //añadir detalle de compra
        for(Detallecompra detallecompra:listadetallecompra){
            iddetalle=optD.size()+2;
            detallecompra.setIddetallecompra(iddetalle);
            detallecompra.setCompra(compra);
            detallecompraRepository.insertDetalleCompra(detallecompra.getCantidad(),
                    detallecompra.getPreciocompra(),detallecompra.getPlantas().getIdplantas(),detallecompra.getCompra().getIdcompra());
        }

        listadetallecompra.clear();
        listacompra.clear();
        contador2=0;
        return "redirect:/cliente/yape";
    }

/*
    @PostMapping(value = "/yape")
    public String yape(@RequestParam("archivo") MultipartFile file,
                                  RedirectAttributes attr,
                                  BindingResult bindingResult,
                                  Model model){
        if (bindingResult.hasErrors()){
            return "cliente/yape";
        }else{

            if(file.isEmpty()){
                model.addAttribute("msg","Debe subir un archivo");
                return "manager/productosnewFrm";
            }


            if(file.getOriginalFilename().contains("..")){
                model.addAttribute("msg","No se permiten '..' en el archivo" );
                return "manager/productosnewFrm";
            }

            try {
                if (!file.isEmpty()) {
                    .setImagen(file.getBytes());
                    plantas.setImagennombre(file.getOriginalFilename());
                    plantas.setImagencontenttype(file.getContentType());
                }


                if (plantas.getIdplantas() == null) {
                    attr.addFlashAttribute("msg", "Producto creado exitosamente");
                }else{
                    attr.addFlashAttribute("msg1", "Producto actualizado exitosamente");
                }
                plantasRepository.save(plantas);
                return "redirect:/manager/list";
            }catch (IOException e){
                e.printStackTrace();
                model.addAttribute("msg","ocurrió un error al subir el archivo");
                return "manager/productosnewFrm";
            }

        }

    }*/
}
