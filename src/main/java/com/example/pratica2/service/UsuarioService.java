package com.example.pratica2.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import com.example.pratica2.models.Tipo;
import org.springframework.ui.Model;

@Service
public class UsuarioService {

    public String processarLogin(String tipoUsuario, HttpSession session) {
        session.setAttribute("authenticated", true);
        session.setAttribute("tipoUsuario", tipoUsuario);

        if (Tipo.ADMIN.name().equals(tipoUsuario)) {
            return "redirect:/produto/list";
        } else {
            return "redirect:/cliente/list";
        }
    }
}