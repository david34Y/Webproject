package com.example.webproject.controller;
import com.example.webproject.entity.Compra;
import com.example.webproject.entity.Plantas;
import com.example.webproject.repository.PlantasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PlantasController {
    @Autowired
    PlantasRepository plantasRepository;
    List<Compra> listacompra =new ArrayList<>();
    int item;
    double totalPagar;
    int numplantas=0;

    static int contador=0;
    @GetMapping(value="/inicio")
    public String inicio1(Model model){
        return "listaPlantas";
    }


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




    //añadir carrito
    @GetMapping("/add")
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


    @GetMapping(value="/carrito")
    public String carrito(Model model){
        totalPagar=0.0;
        for(int i=0;i<listacompra.size();i++){
            totalPagar=totalPagar+ listacompra.get(i).getMonto();
        }
        model.addAttribute("total",totalPagar);
        model.addAttribute("carrito",listacompra);
        System.out.println("+++++++++++++++++++++");
        for (Compra compra:listacompra) {
            System.out.println("ID "+ compra.getIdcompra());
            System.out.println("ID PLANTA "+ compra.getPlantas().getIdplantas()+
                    "  Nombre planta "+ compra.getPlantas().getNombre()+ "  CANTIDAD:"+ compra.getNumplantas());
        }
        model.addAttribute("contador",listacompra.size());
        return "checkout";

    }
    @GetMapping(value="/delete")
    public String del(Model model,@RequestParam("id") String id){
        System.out.println(listacompra.size());
        for(Compra compra1:listacompra){
            System.out.println(compra1.getPlantas().getNombre());
        }

        if(listacompra.size()==1){
            listacompra.remove(0);
            contador=0;
            return "redirect:/shop";
        }
        System.out.println("IDD" + id);
        int id1=Integer.parseInt(id);
        int i=0;
        for (Compra compra:listacompra) {
            if(compra.getPlantas().getIdplantas()==Integer.parseInt(id)) {
                listacompra.remove(i);
                contador=0;
                return "redirect:/carrito";
            }
            i=i+1;
        }


        return "redirect:/carrito";

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
    //detalle de cada planta
    @GetMapping("/shopdetails")
    public String detallestienda(Model model,@RequestParam("id") String id){
        Optional<Plantas> opt= plantasRepository.findById(Integer.parseInt(id));
        if(opt.isPresent()) {
            List<Plantas>planta1=plantasRepository.findplants(Integer.parseInt(id));
            model.addAttribute("idplantas",planta1.get(0).getIdplantas());
            model.addAttribute("planta",planta1.get(0));
            model.addAttribute("contador",contador);
            return "shop-details";
        }

        return "shop-details";
    }
}
