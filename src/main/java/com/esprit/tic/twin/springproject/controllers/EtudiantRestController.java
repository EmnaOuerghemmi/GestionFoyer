package com.esprit.tic.twin.springproject.controllers;

import com.esprit.tic.twin.springproject.services.IEtudiantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class EtudiantRestController {
    IEtudiantService etudiantService;
}
