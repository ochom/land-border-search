/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.countries.routes.handlers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.countries.routes.application.RestService;
import com.countries.routes.models.Country;

/**
 *
 * @author ochom
 */
public class BFS {

    int shortestRoute = 0;
    int runningRoute = 0;
    List<Country> countries = new RestService().getCountries();

    private Country getCountry(String code) {
        return countries.stream().filter(country -> code.equals(country.getCca3())).findFirst().orElse(null);
    }

    public String[] getRoute(String origin, String destination) {
        Country originCountry = getCountry(origin);
        Country destinationCountry = getCountry(destination);

        // if origin and destination are the same, return null
        if (origin.equals(destination)) {
            return null;
        }

        // origin country not found
        if (originCountry == null) {
            return null;
        }

        // destination country not found
        if (destinationCountry == null) {
            return null;
        }

        // if origin country has no borders, return null
        if (originCountry.getBorders().length == 0 || destinationCountry.getBorders().length == 0) {
            return null;
        }

        var resp = shortestRoute(originCountry, destination);
        var paths = resp.substring(1, resp.length()).split(",");

        return paths.length > 2 ? paths : null;
    }

    private String shortestRoute(Country origin, String destination) {
        String path = "";
        HashSet<Country> visited = new HashSet<>();
        Queue<Country> adjacent = new LinkedList<>();
        adjacent.add(origin);
        while (!adjacent.isEmpty()) {
            Country currentCountry = adjacent.remove();
            path += ",\"" + currentCountry.getCca3() + "\"";
            if (Arrays.asList(currentCountry.getBorders()).contains(destination)) {
                return path + ",\"" + destination + "\"";
            }
            for (String border : currentCountry.getBorders()) {
                Country adjacentCountry = getCountry(border);
                if (!visited.contains(adjacentCountry)) {
                    adjacent.add(adjacentCountry);
                }
            }
            visited.add(currentCountry);
        }
        return null;
    }
}
