package ru.sshibko.pastebox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sshibko.pastebox.api.request.PasteBoxRequest;
import ru.sshibko.pastebox.api.response.PasteBoxResponse;
import ru.sshibko.pastebox.api.response.PasteBoxUrlResponse;
import ru.sshibko.pastebox.service.PasteBoxService;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class PasteBoxController {
    private final PasteBoxService pasteBoxService;

    @GetMapping("/{hash}")
    public PasteBoxResponse getByHash(@PathVariable String hash) {
        return pasteBoxService.getByHash(hash);
    }

    @GetMapping("/")
    public Collection<PasteBoxResponse> getPublicPasteList() {
        return pasteBoxService.getFirstPublicPasteBoxes();
    }

    @PostMapping("/")
    public PasteBoxUrlResponse add(@RequestBody PasteBoxRequest request) {
        return pasteBoxService.create(request);
    }

}
