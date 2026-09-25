package mx.edu.utez.tarea_servicios;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/my-services")
public class Controller {

    private final String Nombre_Alumno = "Vanessa Alejandra Miranda Diaz";

    @GetMapping("/fizzbuzz/{n}")
    public String fizzBuzz(@PathVariable int n) {
        if (n < 1) {
            return "El número debe ser mayor o igual a 1.";
        }

        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }

        return Nombre_Alumno;
    }


    @GetMapping("/fibonacci/{n}")
    public String fibonacci(@PathVariable int n) {
        if (n < 1) {
            return "El número debe ser mayor o igual a 1.";
        }

        int a = 0;
        int b = 1;

        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                System.out.println(0);
            } else if (i == 2) {
                System.out.println(1);
            } else {
                int siguiente = a + b;
                System.out.println(siguiente);
                a = b;
                b = siguiente;
            }
        }

        return Nombre_Alumno;
    }
}