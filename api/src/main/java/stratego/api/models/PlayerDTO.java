package stratego.api.models;

import stratego.domain.Playable;

public class PlayerDTO {

	public String name;
	public Playable strategoGame;

	public PlayerDTO(Playable strategoGame, String name) {
		this.strategoGame = strategoGame;
		this.name = name;
	}

	public String getName() {
		return name;
	}

}