package com.example.plantas1.controller;

import com.example.plantas1.entity.Compra;
import com.example.plantas1.entity.Plantas;
import com.example.plantas1.repository.PlantasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
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
                model.addAttribute("contador", listacompra.size());
                return "index";
            } else {
                for(Compra compra:listacompra){
                    if(compra.getPlantas().getIdplantas()==Integer.parseInt(id)){
                        find = 1;
                        numplantas= compra.getNumplantas()+1;
                        compra.setNumplantas(numplantas);
                        compra.setMonto(numplantas * p.getPrecio());
                        model.addAttribute("productList", plantasRepository.plantas());
                        model.addAttribute("contador", listacompra.size());
                        return "index";
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
                model.addAttribute("contador", listacompra.size());
                return "index";
            }

        }
        return "index";

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
        return "carrito";

    }
    @GetMapping(value="/delete")
    public String del(Model model,@RequestParam("id") String id){
        System.out.println("IDD" + id);
        int id1=Integer.parseInt(id);
        int i=0;
        for (Compra compra:listacompra) {
            if(compra.getPlantas().getIdplantas()==Integer.parseInt(id)) {
                listacompra.remove(i);
            }
            i=i+1;
        }

        return "redirect:/carrito";

    }


    @GetMapping(value="/home")
    public String inicio(Model model){
        model.addAttribute("productList", plantasRepository.plantas());
        model.addAttribute("contador",0);
        if(plantasRepository.plantas().get(0).getNombre().isEmpty()){
            System.out.println("FFFFFFFFF");
        }
        System.out.println(plantasRepository.plantas().get(0).getNombre());

        return "index";
    }
}
