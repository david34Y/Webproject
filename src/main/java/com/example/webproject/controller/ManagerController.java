package com.example.webproject.controller;

import com.example.webproject.entity.Compra;
import com.example.webproject.entity.Detallecompra;
import com.example.webproject.entity.Plantas;
import com.example.webproject.repository.CompraRepository;
import com.example.webproject.repository.DetallecompraRepository;
import com.example.webproject.repository.PlantasRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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
import java.util.ArrayList;
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
    public String listarProductos(Model model) throws JsonProcessingException {
        model.addAttribute("productList", plantasRepository.findAll());
        // creas un objeto ObjectMapper para convertir objetos a JSON
        ObjectMapper mapper = new ObjectMapper();

        // conviertes la lista de productos a JSON
        ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
        String json = writer.writeValueAsString(plantasRepository.findAll());

        // Se envia al javascript
        model.addAttribute("productListJSON",json);
        return  "manager/productos";
    }

    @GetMapping(value = "/new")
    public String nuevoProducto(@ModelAttribute("plantas") Plantas plantas){
        return "manager/productosnewFrm";
    }

    @PostMapping(value = "/save")
    public String guardarProducto(@RequestParam("archivo") MultipartFile file,
                                  RedirectAttributes attr,
                                  @ModelAttribute("plantas") Plantas plantas,
                                  BindingResult bindingResult,
                                  Model model){
        if (bindingResult.hasErrors()){
            return "manager/productosnewFrm";
        }else{

            if(file.isEmpty()){
                model.addAttribute("msg","Debe subir un archivo");
                return "manager/productosnewFrm";
            }

            String fileName = file.getOriginalFilename();

            if(fileName.contains("..")){
                model.addAttribute("msg","No se permiten '..' en el archivo" );
                return "manager/productosnewFrm";
            }

            try {
                plantas.setImagen(file.getBytes());
                plantas.setImagennombre(fileName);
                plantas.setImagencontenttype(file.getContentType());
                if (plantas.getIdplantas() == 0) {
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

    @Autowired
    CompraRepository compraRepository;

    @GetMapping(value = "/reports")
    public String listarReportes(Model model){
        model.addAttribute("reportList", detallecompraRepository.findAll());
        return  "manager/reportes";
    }

    @GetMapping(value = "/details")
    public String detallesReportes(@ModelAttribute("detallecompra") Detallecompra detallecompra,
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
        return "redirect:/manager/reports";
    }

}
