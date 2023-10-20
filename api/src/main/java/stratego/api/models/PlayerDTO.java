package stratego.api.models;

import stratego.domain.Playable;

public class PlayerDTO {

	public String name;
	public boolean hasTurn;

	public PlayerDTO(Playable strategoGame, String name) {
		this.name = name;
		this.hasTurn = strategoGame.isPlayersTurn(name);
	}

	public String getName() {
		return name;
	}

	public boolean getHasTurn() {
		return hasTurn;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHasTurn() {
		this.hasTurn = !hasTurn;
	}
}
