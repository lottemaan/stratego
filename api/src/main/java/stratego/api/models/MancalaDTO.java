package stratego.api.models;

import stratego.domain.StrategoGame;

public class MancalaDTO {

    public String name;

    public MancalaDTO(StrategoGame stratego) {
        this.name = stratego.getNameOfPlayerOne();
    }
}
