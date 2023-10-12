package stratego.api.models;

import stratego.domain.StrategoGame;

public class PlayerDTO {

	public String name;
	public StrategoGame strategoGame;

	public PlayerDTO(StrategoGame strategoGame, String name) {
		this.strategoGame = strategoGame;
		this.name = name;
	}

	public String getName() {
		return name;
	}

}