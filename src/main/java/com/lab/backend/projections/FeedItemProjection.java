package com.lab.backend.projections;

import java.time.LocalDateTime;

public interface FeedItemProjection {
    Long getId();
    String getTipo();
    String getTitulo();
    String getResumo();
    LocalDateTime getInstante();

    Long getLabId();
    String getLabNome();
    String getAccent();

    Long getAuthorId();
    String getAuthorNome();
    String getAuthorPhoto();
}
