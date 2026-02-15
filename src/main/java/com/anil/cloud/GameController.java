package com.example.cloud;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.Random;

@Controller
public class GameController {

    @GetMapping("/")
    public String startGame(HttpSession session, Model model) {

        if (session.getAttribute("number") == null) {
            Random gen = new Random();
            int num = gen.nextInt(100) + 1;
            session.setAttribute("number", num);
            session.setAttribute("attempts", 0);
        }

        model.addAttribute("message", "Enter your guess between 1 to 100");
        return "game";
    }

    @PostMapping("/guess")
    public String checkGuess(@RequestParam int guess,
                             HttpSession session,
                             Model model) {

        int num = (int) session.getAttribute("number");
        int attempts = (int) session.getAttribute("attempts");

        attempts++;
        session.setAttribute("attempts", attempts);

        if (guess > num) {
            model.addAttribute("message", "Your guess is greater than number");
        }
        else if (guess < num) {
            model.addAttribute("message", "Your guess is lesser than the number");
        }
        else {
            model.addAttribute("message",
                    "You Nailed it broooooo !!! 🎉 in " + attempts + " attempts");
            session.removeAttribute("number");
            session.removeAttribute("attempts");
        }

        return "game";
    }
}
