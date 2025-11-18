package com.example.demo.config;

import com.example.demo.model.entity.ProductoEntity;
import com.example.demo.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    
    private final String BASE_IMAGE_URL = "http://10.0.2.2:8080/images/";

    @Bean
    CommandLineRunner initDatabase(ProductoRepository repository) {
        return args -> {
            
            if (repository.count() == 0) {
                System.out.println("Poblando la base de datos con productos iniciales...");

                // 1. PC GAMER RDY Y70 TI B04
                ProductoEntity p1 = new ProductoEntity(); 
                p1.setNombre("PC GAMER RDY Y70 TI B04");
                p1.setPrecio(2499000);
                p1.setImagen(BASE_IMAGE_URL + "pc2.webp"); //
                p1.setDescripcion("Intel® Core™ i9-14900KF, MSI PRO Z790-A MAX Wi-Fi");
                p1.setCategoria("PC Gamer");

                // 2. PC GAMER RDY Y70 Liquid Hybrid Max
                ProductoEntity p2 = new ProductoEntity();
                p2.setNombre("PC GAMER RDY Y70 Liquid Hybrid Max");
                p2.setPrecio(2000000);
                p2.setImagen(BASE_IMAGE_URL + "pc3.webp"); //
                p2.setDescripcion("Intel® Core™ Ultra 9, ASUS ROG STRIX Z890-E WIFI");
                p2.setCategoria("PC Gamer");

                // 3. TECLADO GAMER REDRAGON UCAL K673
                ProductoEntity p3 = new ProductoEntity();
                p3.setNombre("TECLADO GAMER REDRAGON UCAL K673");
                p3.setPrecio(62350);
                p3.setImagen(BASE_IMAGE_URL + "teclado1.webp"); //
                p3.setDescripcion("Keycaps PBT Premium");
                p3.setCategoria("Teclado");

                // 4. TECLADO REDRAGON K530W DRACONIC BLC
                ProductoEntity p4 = new ProductoEntity();
                p4.setNombre("TECLADO REDRAGON K530W DRACONIC BLC");
                p4.setPrecio(70350);
                p4.setImagen(BASE_IMAGE_URL + "tecladob1.webp"); //
                p4.setDescripcion("Keycaps PBT Premium");
                p4.setCategoria("Teclado");

                // 5. GAMING MOUSE Logitech G305
                ProductoEntity p5 = new ProductoEntity();
                p5.setNombre("GAMING MOUSE Logitech G305");
                p5.setPrecio(68000);
                p5.setImagen(BASE_IMAGE_URL + "mouse1loghitech.webp"); //
                p5.setDescripcion("Sensor HERO");
                p5.setCategoria("Mouse");

                // 6. SILLA GAMER COUGAR TERMINATOR
                ProductoEntity p6 = new ProductoEntity();
                p6.setNombre("SILLA GAMER COUGAR TERMINATOR");
                p6.setPrecio(265000);
                p6.setImagen(BASE_IMAGE_URL + "sillaterminator.png"); //
                p6.setDescripcion("Polipiel Hyper-Dura");
                p6.setCategoria("Silla");

                // 7. CONSOLA PS5
                ProductoEntity p7 = new ProductoEntity();
                p7.setNombre("CONSOLA PS5");
                p7.setPrecio(699000);
                p7.setImagen(BASE_IMAGE_URL + "ps5.png"); //
                p7.setDescripcion("500 GB, lector Blu-ray 4K");
                p7.setCategoria("Consola");

                // 8. CONTROL GAME PAD XBOX
                ProductoEntity p8 = new ProductoEntity();
                p8.setNombre("CONTROL GAME PAD XBOX");
                p8.setPrecio(55000);
                p8.setImagen(BASE_IMAGE_URL + "controlxbox.png"); //
                p8.setDescripcion("Inalámbrico Sky Cipher");
                p8.setCategoria("Control");

                
                repository.saveAll(List.of(p1, p2, p3, p4, p5, p6, p7, p8));
                
                System.out.println("¡8 productos iniciales cargados en la base de datos!");
            } else {
                System.out.println("La base de datos ya contiene datos. No se cargaron productos iniciales.");
            }
        };
    }
}