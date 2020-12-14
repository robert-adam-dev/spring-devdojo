package academy.devdojo.springboot2.controller;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.dto.AnimeDto;
import academy.devdojo.springboot2.dto.AnimeUpdateDto;
import academy.devdojo.springboot2.service.AnimeService;
import academy.devdojo.springboot2.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("animes")
@Log4j2
@RequiredArgsConstructor
public class AnimeController {
    private final DateUtil dateUtil;
    private final AnimeService animeService;

    @GetMapping
    public ResponseEntity<List<Anime>> list(){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.listAll());
    }

    @GetMapping("/find")
    public ResponseEntity<List<Anime>> findByName(@RequestParam String name){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.findByName(name));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> findById(@PathVariable long id){
        return ResponseEntity.ok(animeService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody @Valid AnimeDto animeDto){
        return new ResponseEntity<>(animeService.save(animeDto), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody AnimeUpdateDto animeUpdateDto){
        animeService.replace(animeUpdateDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
