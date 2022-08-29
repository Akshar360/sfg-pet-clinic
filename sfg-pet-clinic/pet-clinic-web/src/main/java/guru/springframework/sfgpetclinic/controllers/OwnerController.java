package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners") //class lever request mapping
@Controller
public class OwnerController
{
    @RequestMapping({"","/","/index","/index.html"})
    public String listOwners()
    {
        return "owners/index"; //return name of the template
    }
}
