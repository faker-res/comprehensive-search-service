package la.niub.abcapi.servicecompre.controller;

import la.niub.abcapi.servicecompre.component.rbac.AuthSdk;
import la.niub.abcapi.servicecompre.model.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/access")
public class AccessController {

    @Autowired
    AuthSdk authSdk;
    @GetMapping("/getAccess")
    Response getAccess(@RequestParam("userId") String userId) {
        return new Response(authSdk.getAccess(userId));
    }

}
