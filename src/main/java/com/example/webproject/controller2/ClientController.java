package com.example.webproject.controller2;


import com.example.webproject.entity.*;
import com.example.webproject.repository.PlantasRepository;
import com.example.webproject.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    int item=0;
    double totalPagar;
    int numplantas=0;

    static int contador=0;
    @GetMapping("/index")
    public String indice(Model model){

        model.addAttribute("contador",contador);
        return "index";
    }

    @GetMapping("/about")
    public String acercade(Model model){
        model.addAttribute("contador",contador);
        return "about";
    }

    @GetMapping(value="/shop")
    public String inicio(Model model){

        model.addAttribute("productList", plantasRepository.plantas());

        model.addAttribute("contador",contador);
        if(plantasRepository.plantas().get(0).getNombre().isEmpty()){
            System.out.println("FFFFFFFFF");
        }
        System.out.println(plantasRepository.plantas().get(0).getNombre());

        return "shop";
    }

    //Detalle por planta
    @GetMapping("/shopdetails")
    public String detallestienda(Model model,@RequestParam("id") String id){
        Optional<Plantas> opt= plantasRepository.findById(Integer.parseInt(id));
        if(opt.isPresent()) {
            List<Plantas> planta1=plantasRepository.findplants(Integer.parseInt(id));
            model.addAttribute("idplantas",planta1.get(0).getIdplantas());
            model.addAttribute("planta",planta1.get(0));
            model.addAttribute("contador",contador);
            return "shop-details";
        }

        return "shop-details";
    }

    //---------------------PUBLICACIONES-----------------------------------
    @GetMapping("/publicaciones")
    public String blog(Model model){
        model.addAttribute("contador",contador);
        List<Publicacion> publicaciones=publicacionRepository.publicaciones();
        for(Publicacion publicacion:publicaciones){
            System.out.println(publicacion.getResumen());
            System.out.println(publicacion.getTexto());
            System.out.println(publicacion.getTitulo());
        }
        model.addAttribute("publicaciones",publicaciones);

        return "blog";
    }


    @GetMapping("/single_post")
    public String postunico(Model model, @RequestParam("id") String id){
        System.out.println(id);
        model.addAttribute("contador",contador);
        Optional<Publicacion> opt= publicacionRepository.findById(Integer.parseInt(id));
        if(opt.isPresent()) {
            List<Publicacion> publicacion=publicacionRepository.findpubli(Integer.parseInt(id));
            for(Publicacion publicacion1:publicacion){
                System.out.println(publicacion1.getResumen());
                System.out.println(publicacion1.getTexto());
                System.out.println(publicacion1.getTitulo());
            }
            model.addAttribute("publicacion",publicacion.get(0));
            model.addAttribute("contador",contador);
            return "single-post";
        }
        return "single-post";
    }

    //--------------------CONTACTO------------------------

    @GetMapping("/contact")
    public String contacto(@ModelAttribute("consultante") Consulta consultante, Model model){
        model.addAttribute("contador",contador);
        return "contact";
    }

    //---------------------------------CARRITO---------------------------------------------------

    List<Compra> listacompra =new ArrayList<>();
    List<Detallecompra> listadetallecompra=new ArrayList<>();



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
        return "checkout";

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
                contador=listadetallecompra.size();
                model.addAttribute("contador", contador);
                return "shop";
            } else {
                for(Detallecompra compra:listadetallecompra){
                    if(compra.getPlantas().getIdplantas()==Integer.parseInt(id)){
                        find = 1;
                        numplantas= compra.getCantidad()+1;
                        compra.setCantidad(numplantas);
                        compra.setPreciocompra(numplantas * p.getPrecio());
                        model.addAttribute("productList", plantasRepository.plantas());
                        contador=listadetallecompra.size();
                        model.addAttribute("contador", contador);
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
                contador=listadetallecompra.size();
                model.addAttribute("contador", contador);
                return "shop";
            }
        }
        return "shop";

    }



    //añadir carrito anterior
    @GetMapping("/add2")
    public String add(Model model,@RequestParam("id") String id){
        Optional<Plantas> opt= plantasRepository.findById(Integer.parseInt(id));
        numplantas=0;
        int find=0;
        int findid;

        if(opt.isPresent()) {
            //obtengo la planta
            Plantas p = opt.get();
            //obtengo planta
            if(listacompra.size()==0){
                numplantas = numplantas + 1;
                Compra compra = new Compra();
                compra.setIdcompra(item);
                compra.setNumplantas(numplantas);
                compra.setIdcompra(p.getIdplantas());
                compra.setPlantas(p);
                compra.setMonto(numplantas * p.getPrecio());
                listacompra.add(compra);
                model.addAttribute("productList", plantasRepository.plantas());
                contador=listacompra.size();
                model.addAttribute("contador", contador);
                return "shop";
            } else {
                for(Compra compra:listacompra){
                    if(compra.getPlantas().getIdplantas()==Integer.parseInt(id)){
                        find = 1;
                        numplantas= compra.getNumplantas()+1;
                        compra.setNumplantas(numplantas);
                        compra.setMonto(numplantas * p.getPrecio());
                        model.addAttribute("productList", plantasRepository.plantas());
                        contador=listacompra.size();
                        model.addAttribute("contador", contador);
                        return "shop";
                    }
                }
                numplantas = numplantas + 1;
                Compra compra = new Compra();
                compra.setIdcompra(item);
                compra.setNumplantas(numplantas);
                compra.setIdcompra(p.getIdplantas());
                compra.setPlantas(p);
                compra.setMonto(numplantas * p.getPrecio());
                listacompra.add(compra);
                model.addAttribute("productList", plantasRepository.plantas());
                contador=listacompra.size();
                model.addAttribute("contador", contador);
                return "shop";
            }

        }
        return "shop";

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
            contador=0;
            return "redirect:/shop";
        }
        System.out.println("IDD" + id);
        int id1=Integer.parseInt(id);
        int i=0;
        for (Detallecompra compra:listadetallecompra) {
            if(compra.getPlantas().getIdplantas()==Integer.parseInt(id)) {
                listadetallecompra.remove(i);
                contador=0;
                return "redirect:/carrito";
            }
            i=i+1;
        }


        return "redirect:/carrito";

    }

    //------------------------------------------------------------
    //archivo
    @GetMapping("/imagen/{id}")
    public ResponseEntity<byte[]> mostrarImagen(@PathVariable ("id") int id){
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
}
