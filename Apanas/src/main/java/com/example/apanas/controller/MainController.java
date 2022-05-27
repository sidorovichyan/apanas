package com.example.apanas.controller;


import com.example.apanas.count.CountAtomic;
import com.example.apanas.entity.Triangle;
import com.example.apanas.exeption.ExceptionData;
import com.example.apanas.hash.HashCalculation;
import com.example.apanas.logic.MathFind;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class MainController {


    private HashCalculation hashCalculation;

    private CountAtomic countAtomic;

    @Autowired                                                                            // spring иньекция
    public void sethashCalculation(HashCalculation hashCalculation) {
        this.hashCalculation = hashCalculation;
    }

    @Autowired
    public void setCountAtomic(CountAtomic countAtomic) {
        this.countAtomic = countAtomic;
    }


    Logger logger = LogManager.getLogger(MainController.class);

    @GetMapping("/")
    public ResponseEntity<String> homeView(@RequestParam("first") Integer value1,
                                           @RequestParam("second") Integer value2,
                                           @RequestParam("thrid") Integer value3,
                                           Model model) throws ExceptionData {

        countAtomic.increment();

        if (value1 < 0 || value2 < 0 || value3 < 0) {
            throw new ExceptionData("Error data");
        }

        Triangle triangle = new Triangle(value1, value2, value3);
        String rezult;
        if (hashCalculation.isContain(triangle)) {
            logger.info("get from HashMap by key: " + triangle.hashCode());
            rezult = hashCalculation.getParameters(triangle);
        } else {
            Integer perimetr = MathFind.findPerimetr(triangle);

            Double s = MathFind.findS(triangle);

            rezult = "Perim:" + perimetr + " S: " + s;

            hashCalculation.addToMap(triangle, rezult);
            logger.info("add to HashMap key: " + triangle.hashCode());
        }
        return new ResponseEntity<>(rezult, HttpStatus.OK);
    }

    @PostMapping("/bulk")
    public ResponseEntity<?> doBulk(@RequestBody List<Triangle> params) {

        List<Integer> rezultsPerim = new ArrayList<>();
        List<Double> rezultS = new ArrayList<>();

        for (Triangle smth:params) {
                rezultsPerim.add(MathFind.findPerimetr(smth));
        }

        for (Triangle smth:params) {
            rezultS.add(MathFind.findS(smth));
        }

        Map<String, Object> mapa = new HashMap<>();


        mapa.put("avergeS", rezultS
                .stream()
                .mapToDouble(d -> d)
                .average()
                .orElseThrow(NoSuchElementException::new));

        mapa.put("avergePerim", rezultsPerim
                .stream()
                .mapToInt(i -> i)
                .average()
                .orElseThrow(NoSuchElementException::new));

        mapa.put("maxPerim", rezultsPerim
                .stream()
                .mapToInt(i -> i)
                .max()
                .orElseThrow(NoSuchElementException::new));

        mapa.put("minPerim", rezultsPerim
                .stream()
                .mapToInt(i -> i)
                .min()
                .orElseThrow(NoSuchElementException::new));



        return new ResponseEntity<>(mapa,HttpStatus.OK);
    }




    @GetMapping("/counter")
    public ResponseEntity<String> counter()
    {
        return new ResponseEntity<>(CountAtomic.value()+"",HttpStatus.OK);
    }


    @ExceptionHandler(ExceptionData.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    // обработчик unchecked ошибок
    public ResponseEntity<String> handleMyException(ExceptionData e) {
        logger.warn("error 400");
        return new ResponseEntity<>("<h1>Error 400<br></h1>" + ExceptionData.class + e.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    // обработчик unchecked ошибок
    public ResponseEntity<String> handleUncheced(RuntimeException e) {
        logger.warn("error 500");
        return new ResponseEntity<>("<h1>Error 500<br></h1>" + RuntimeException.class + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
