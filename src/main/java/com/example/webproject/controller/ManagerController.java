package com.example.webproject.controller;

import com.example.webproject.entity.Compra;
import com.example.webproject.entity.Detallecompra;
import com.example.webproject.entity.Plantas;
import com.example.webproject.entity.Usuario;
import com.example.webproject.repository.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/manager")
public class ManagerController {
    @Autowired
    PlantasRepository plantasRepository;

    @Autowired
    DetallecompraRepository detallecompraRepository;

    @GetMapping(value={"/list","/",""})
    public String listarProductos(Model model, HttpSession session) throws JsonProcessingException {
        model.addAttribute("productList", plantasRepository.findAll());
        // creas un objeto ObjectMapper para convertir objetos a JSON
        ObjectMapper mapper = new ObjectMapper();

        Usuario usuario = (Usuario) session.getAttribute("user");

        // conviertes la lista de productos a JSON
        ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
        String json = writer.writeValueAsString(plantasRepository.findAll());

        // Se envia al javascript
        model.addAttribute("productListJSON",json);
        model.addAttribute("user", usuario);
        return  "manager/productos";
    }

    @GetMapping(value = "/new")
    public String nuevoProducto(@ModelAttribute("plantas") Plantas plantas){
        return "manager/productosnewFrm";
    }

    @PostMapping(value = "/save")
    public String guardarProducto(@RequestParam("archivo") MultipartFile file,
                                  @RequestParam("foto1") MultipartFile file1,
                                  @RequestParam("foto2") MultipartFile file2,
                                  RedirectAttributes attr,
                                  @ModelAttribute("plantas") Plantas plantas,
                                  BindingResult bindingResult,
                                  Model model){
        if (bindingResult.hasErrors()){
            return "manager/productosnewFrm";
        }else{
            /*
            if(file.isEmpty()){
                model.addAttribute("msg","Debe subir un archivo");
                return "manager/productosnewFrm";
            }*/


            if(file.getOriginalFilename().contains("..")){
                model.addAttribute("msg","No se permiten '..' en el archivo" );
                return "manager/productosnewFrm";
            }
/*
            if(file1.isEmpty()){
                model.addAttribute("msg","Debe subir una foto");
                return "manager/productosnewFrm";
            }*/


            if(file1.getOriginalFilename().contains("..")){
                model.addAttribute("msg","No se permiten '..' en la foto" );
                return "manager/productosnewFrm";
            }
/*
            if(file2.isEmpty()){
                model.addAttribute("msg","Debe subir otra foto");
                return "manager/productosnewFrm";
            }*/


            if(file2.getOriginalFilename().contains("..")){
                model.addAttribute("msg","No se permiten '..' en la otra foto" );
                return "manager/productosnewFrm";
            }

            try {
                if (!file.isEmpty()) {
                    plantas.setImagen(file.getBytes());
                    plantas.setImagennombre(file.getOriginalFilename());
                    plantas.setImagencontenttype(file.getContentType());
                }
                if (!file1.isEmpty()) {
                    plantas.setImagen2(file1.getBytes());
                    plantas.setImagennombre2(file1.getOriginalFilename());
                    plantas.setImagencontenttype2(file1.getContentType());
                }
                if (!file2.isEmpty()) {
                    plantas.setImagen3(file2.getBytes());
                    plantas.setImagennombre3(file2.getOriginalFilename());
                    plantas.setImagencontenttype3(file2.getContentType());
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

    }

    @GetMapping(value = "/edit")
    public String editarProducto(@ModelAttribute("plantas") Plantas plantas,
                                 Model model,
                                 @RequestParam("id") int id){
        Optional<Plantas> optionalPlantas = plantasRepository.findById(id);

        if(optionalPlantas.isPresent()){
            plantas= optionalPlantas.get();
            model.addAttribute("plantas",plantas);

            return "manager/productosnewFrm";
        }else{

            return "redirect:/manager/list";
        }
    }

    @GetMapping(value = "/delete")
    public String eliminarProducto(RedirectAttributes attr,
                                   @RequestParam("id") int id){
        Optional<Plantas> optionalPlantas = plantasRepository.findById(id);
        if(optionalPlantas.isPresent()) {
            // Verificar si la planta tiene compras asociadas en el repositorio DetallecompraRepository
            List<Detallecompra> comprasAsociadas = detallecompraRepository.findByPlantasID(id);
            if (comprasAsociadas.isEmpty()) {
                plantasRepository.deleteById(id);
                attr.addFlashAttribute("msg2", "Producto borrado exitosamente");
            } else {
                attr.addFlashAttribute("msg2", "No se puede borrar el producto porque tiene compras asociadas");
            }
        }
        return "redirect:/manager/list";
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> mostrarImagen(@PathVariable("id") int id){
        Optional<Plantas> opt = plantasRepository.findById(id);
        if(opt.isPresent()){
            Plantas p = opt.get();

            byte[] imagenComoBytes = p.getImagen();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(
                    MediaType.parseMediaType(p.getImagencontenttype())
            );

            return new ResponseEntity<>(
                    imagenComoBytes,
                    httpHeaders,
                    HttpStatus.OK
            );
        }else{
            return null;
        }
    }

    @GetMapping("/yape/{id}")
    public ResponseEntity<byte[]> mostrarYape(@PathVariable("id") int id){
        Optional<Compra> opt = compraRepository.findById(id);
        if(opt.isPresent()){
            Compra p = opt.get();

            byte[] imagenComoBytes = p.getImagen();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(
                    MediaType.parseMediaType(p.getImagencontenttype())
            );

            return new ResponseEntity<>(
                    imagenComoBytes,
                    httpHeaders,
                    HttpStatus.OK
            );
        }else{
            return null;
        }
    }

    @GetMapping("/image1/{id}")
    public ResponseEntity<byte[]> mostrarImagen2(@PathVariable ("id") int id){
        Optional<Plantas> opt= plantasRepository.findById(id);
        if(opt.isPresent()){
            Plantas p= opt.get();
            //System.out.println("AAAAAAAAAAAAAAAAAAAAAA");
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

    @GetMapping("/image2/{id}")
    public ResponseEntity<byte[]> mostrarImagen3(@PathVariable ("id") int id){
        Optional<Plantas> opt= plantasRepository.findById(id);
        if(opt.isPresent()){
            Plantas p= opt.get();
            //System.out.println("AAAAAAAAAAAAAAAAAAAAAA");
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

    @Autowired
    CompraRepository compraRepository;


    @GetMapping(value = "/achats")
    public String listarCompras(Model model){
        model.addAttribute("reportList", detallecompraRepository.findAll());
        return "manager/compras";
    }

    @GetMapping(value = "/details")
    public String detallesCompras(@ModelAttribute("detallecompra") Detallecompra detallecompra,
                                   @RequestParam("id") int id,
                                   Model model){

        model.addAttribute("compraList",detallecompraRepository.findByComprasID(id));
        return "manager/detallesCompra";
    }


    @GetMapping(value = "/state")
    public String actualizarEstado(@ModelAttribute("detallecompra") Detallecompra detallecompra,
                                   @RequestParam("id") int id,
                                   RedirectAttributes attr){

        Optional<Compra> opt = compraRepository.findById(id);
        if(opt.isPresent()){
            Compra compra = opt.get();

            if (compra.getEstado().equals("Completa")){
                attr.addFlashAttribute("msg", "La compra ya se habia procesado antes");
            }else{
                compraRepository.actualizarEstado(id);
                attr.addFlashAttribute("msg", "Compra actualizado exitosamente");

            }
        }
        return "redirect:/manager/achats";
    }

    @Autowired
    UsuarioRepository usuarioRepository;
    @GetMapping(value = "/clients")
    public String listarClientes(Model model){
        model.addAttribute("clientList", usuarioRepository.findAllClients());
        return  "manager/clientes";
    }

    @GetMapping(value = "/reports")
    public String listarReportes(Model model){
        model.addAttribute("reportList",detallecompraRepository.findByCompraCompletada());
        model.addAttribute("montoTotal",compraRepository.obtenerMontoTotal());
        return "manager/reportes";
    }

    @PostMapping("/searchProduct")
    public String buscarProductos(@RequestParam("searchField") String searchField,
                                  Model model){
        if (searchField.equals("")){
            model.addAttribute("productList",plantasRepository.findAll());
        }else{
            model.addAttribute("productList",plantasRepository.findByNombre(searchField));
        }

        return "manager/productos";
    }

    @PostMapping("/searchClient")
    public String buscarClientes(@RequestParam("searchField") String searchField,
                                  Model model){
        if (searchField.equals("")){
            model.addAttribute("clientList",usuarioRepository.findAllClients());
        }else{
            model.addAttribute("clientList",usuarioRepository.findSpecificClient(searchField));
        }

        return "manager/clientes";
    }




}
