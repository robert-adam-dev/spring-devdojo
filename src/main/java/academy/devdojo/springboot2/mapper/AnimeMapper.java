package academy.devdojo.springboot2.mapper;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.dto.AnimeDto;
import academy.devdojo.springboot2.dto.AnimeUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {

    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);
    public abstract Anime toAnime(AnimeDto animeDto);
    public abstract Anime toAnime(AnimeUpdateDto animeUpdateDto);
}
