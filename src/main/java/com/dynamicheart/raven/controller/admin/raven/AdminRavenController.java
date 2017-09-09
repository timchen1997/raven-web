package com.dynamicheart.raven.controller.admin.raven;

import com.dynamicheart.raven.authorization.annotation.Authorization;
import com.dynamicheart.raven.controller.app.raven.populator.RavenInfoFieldsPopulator;
import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.model.raven.Raven;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.services.house.HouseService;
import com.dynamicheart.raven.services.raven.RavenService;
import com.dynamicheart.raven.services.user.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/admin/api/v1/ravens")
public class AdminRavenController {
    @Inject
    private RavenService ravenService;

    @Inject
    private UserService userService;

    @Inject
    private HouseService houseService;

    @RequestMapping(value = "searchByAddresser/{userId}", method = RequestMethod.GET)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = List.class, message = "Get ravens by sender id")
    })
    public ResponseEntity<?> searchByAddresser(@PathVariable String userId)throws Exception{
        if(userService.exists(userId))
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);

        List<Raven> ravens = ravenService.findByAddresserId(userId);

        return new ResponseEntity<>(ravens,HttpStatus.OK);
    }

    @RequestMapping(value = "searchByDescription/{content}", method = RequestMethod.GET)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = List.class, message = "Get ravens by content")
    })
    public ResponseEntity<?> searchByDescription(@PathVariable String content)throws Exception{
        List<Raven> ravens = ravenService.findBySimilarContent(content);

        if(ravens.size()==0)
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(ravens,HttpStatus.OK);
    }

    @RequestMapping(value = "searchByHouse/{houseId}", method = RequestMethod.GET)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = List.class, message = "Get ravens by group id")
    })
    public ResponseEntity<?> searchByHouse(@PathVariable String houseId)throws Exception{
        if(houseService.exists(houseId))
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);

        List<Raven> ravens = ravenService.findByHouseId(houseId);

        return new ResponseEntity<>(ravens,HttpStatus.OK);
    }
}
