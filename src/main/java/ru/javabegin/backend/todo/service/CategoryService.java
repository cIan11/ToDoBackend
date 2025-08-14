package ru.javabegin.backend.todo.service;

import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.javabegin.backend.todo.entity.Category;
import ru.javabegin.backend.todo.repo.CategoryRepository;


import java.util.List;


@Service
@Transactional
public class CategoryService {

    // работает встроенный механизм DI из Spring, который при старте приложения подставит в эту переменную нужные класс-реализацию
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Cacheable(cacheNames = "categories")
    public List<Category> findAll(String email) {
        return repository.findByUserEmailOrderByTitleAsc(email);
    }

    public Category add(Category category) {
        return repository.save(category);
    }

    public Category update(Category category) {
        return repository.save(category);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    // поиск категорий пользователя по названию
    public List<Category> findByTitle(String text, String email) {
        return repository.findByTitle(text, email);
    }

    // поиск категории по ID
    public Category findById(Long id) {
        return repository.findById(id).get(); // т.к. возвращается Optional - можно получить объект методом get()
    }


}
