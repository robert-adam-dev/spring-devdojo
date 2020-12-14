package academy.devdojo.springboot2.service;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.dto.AnimeDto;
import academy.devdojo.springboot2.dto.AnimeUpdateDto;
import academy.devdojo.springboot2.exception.BadRequestException;
import academy.devdojo.springboot2.mapper.AnimeMapper;
import academy.devdojo.springboot2.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    @Autowired
    private AnimeRepository animeRepository;

    public List<Anime> listAll() {
        return animeRepository.findAll();
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }

    public Anime findByIdOrThrowBadRequestException(long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime not Found"));
    }

    @Transactional // Por padrão, funciona somente em exceções unchecked
    public Anime save(AnimeDto animeDto) {
        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animeDto));
    }

    @Transactional
    public void delete(long id) {
        animeRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    @Transactional
    public void replace(AnimeUpdateDto animeUpdateDto) {
        Anime savedAnime = findByIdOrThrowBadRequestException(animeUpdateDto.getId());
        Anime anime = AnimeMapper.INSTANCE.toAnime(animeUpdateDto);
        anime.setId(savedAnime.getId());
        animeRepository.save(anime);
    }
}
