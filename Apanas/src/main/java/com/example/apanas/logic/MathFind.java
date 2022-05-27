package com.example.apanas.logic;

import com.example.apanas.entity.Triangle;

public class MathFind {

    public static Integer findPerimetr(Triangle triangle)
    {
       return triangle.getFirstStoron()+triangle.getSecondStoron()+triangle.getThidStoron();
    }

    public static Double findS (Triangle triangle)
    {
        Double p = (double) ((triangle.getSecondStoron() + triangle.getFirstStoron() + triangle.getThidStoron()) / 2);
        return Math.sqrt(p * (p - triangle.getFirstStoron()) * (p - triangle.getSecondStoron()) * (p -  triangle.getThidStoron()));
    }


}
