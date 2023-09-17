package solahkay.binar.challenge.repository;

import solahkay.binar.challenge.entity.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuRepository {

    List<Menu> findAll();

    Optional<Menu> getById(Long id);

    boolean insert(Menu menu);

}
