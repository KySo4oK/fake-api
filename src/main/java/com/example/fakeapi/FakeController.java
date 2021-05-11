package com.example.fakeapi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fake")
@Api("fake controller")
public class FakeController {

    @GetMapping
    @ApiOperation("get simple fake entity")
    FakeEntity getEntity() {
        return new FakeEntity(1, "fakeName");
    }

    @PutMapping("/{id}")
    @ApiOperation("update simple fake entity")
    FakeEntity updateEntity(@RequestBody FakeEntity entity, @PathVariable String id) {
        if (StringUtils.isEmpty(entity.getName())) {
            throw new ValidationException("name to update cannot be empty");
        }
        return entity;
    }

    @PostMapping
    @ApiOperation("create simple fake entity")
    FakeEntity createEntity(@RequestBody FakeEntity entity) {
        if (StringUtils.isEmpty(entity.getName())) {
            throw new ValidationException("name to create cannot be empty");
        }
        entity.setId(123);
        return entity;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @ApiOperation("delete simple fake entity")
    void delete(@PathVariable String id) {
        if (Integer.parseInt(id) < 0) {
            throw new ValidationException("id cannot be negative");
        }
    }

}
