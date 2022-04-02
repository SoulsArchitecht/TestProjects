package ru.sshibko.pastebox.repository;

import org.springframework.stereotype.Repository;
import ru.sshibko.pastebox.exception.NotFoundEntityException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class PasteBoxRepositoryMap implements PasteBoxRepository {

    private final Map<String, PasteBoxEntity> vault = new ConcurrentHashMap<>();

    @Override
    public PasteBoxEntity getByHash(String hash) {
        PasteBoxEntity pasteBoxEntity = vault.get(hash);
        if (pasteBoxEntity == null) {
            throw new NotFoundEntityException("PasteBox not found with hash = ");

        }
        return pasteBoxEntity;
    }

    @Override
    public List<PasteBoxEntity> getListOfPublicAndAlive(int amount) {
        LocalDateTime now = LocalDateTime.now();
        return vault.values().stream()
                .filter(PasteBoxEntity::isPublic)
                .filter(pasteBoxEntity -> pasteBoxEntity.getLifetime().isAfter(now))
                .sorted(Comparator.comparing(PasteBoxEntity::getId))
                .limit(amount)
                .collect(Collectors.toList());
    }

    @Override
    public void add(PasteBoxEntity pasteBoxEntity) {

        vault.put(pasteBoxEntity.getHash(), pasteBoxEntity);
    }
}
