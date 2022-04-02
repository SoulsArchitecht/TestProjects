package ru.sshibko.pastebox.api.request;

import lombok.Data;
import ru.sshibko.pastebox.api.PublicStatus;

@Data
public class PasteBoxRequest {
    private String data;
    private long expirationTimeSeconds;
    private PublicStatus publicStatus;

}
