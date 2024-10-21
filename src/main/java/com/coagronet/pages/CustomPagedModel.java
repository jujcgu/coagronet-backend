package com.coagronet.pages;

import java.util.Collection;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomPagedModel<T> extends PagedModel<T> {

    public CustomPagedModel() {
        super();
    }

    public CustomPagedModel(Collection<T> content, PageMetadata metadata, Iterable<Link> links) {
        super(content, metadata, links);
    }

    public CustomPagedModel(Collection<T> content, PageMetadata metadata) {
        super(content, metadata);
    }

    @JsonProperty("_embedded")
    public CustomEmbedded<T> getCustomEmbedded() {
        return new CustomEmbedded<>(super.getContent());
    }

    public static class CustomEmbedded<T> {
        @JsonProperty("data")
        private final Collection<T> data;

        public CustomEmbedded(Collection<T> data) {
            this.data = data;
        }

        public Collection<T> getData() {
            return data;
        }
    }

    // Eliminar esta anotación para evitar conflictos
    // @JsonProperty("data")
    // public Collection<T> getData() {
    // return super.getContent();
    // }
}
