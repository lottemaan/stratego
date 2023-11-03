package stratego.domain;

abstract class Piece {
    private String namePiece;
    private Player player;
    private boolean active = true;
    private boolean battleWon = false;
    private boolean battleLost = false;

    protected String getNamePiece() {
        return this.namePiece;
    }

    protected void setNamePiece(String name) {
        this.namePiece = name;
    }

    protected boolean isActive() {
        return this.active;
    }

    protected void fall() {
        this.active = false;
        this.hasLostTheBattle();
    }

    protected void win() {
        this.active = true;
        this.hasWonTheBattle();
    }

    protected Player getPlayer() {
        return this.player;
    }

    protected void assignPlayer(Player player) {
        this.player = player;
    }

    protected boolean hasBattleWon() {
        return this.battleWon;
    }

    protected void hasWonTheBattle() {
        this.battleWon = true;
    }

    protected void resetBattleWon() {
        this.battleWon = false;
    }

    protected boolean hasBattleLost() {
        return this.battleLost;
    }

    protected void hasLostTheBattle() {
        this.battleLost = true;
    }
}

abstract class DynamicPiece extends Piece {
    protected int rank;

    protected int getRank() {
        return this.rank;
    }
}

abstract class StaticPiece extends Piece {

}

final class Marshal extends DynamicPiece {

    protected Marshal() {
        this.setNamePiece("marshal");
        this.rank = 1;
    }
}

final class Spy extends DynamicPiece {

    protected Spy() {
        this.setNamePiece("spy");
        this.rank = 10;
    }
}

final class Scout extends DynamicPiece {
    protected Scout() {
        this.setNamePiece("scout");
        this.rank = 9;
    }
}

final class Miner extends DynamicPiece {
    protected Miner() {
        this.setNamePiece("miner");
        this.rank = 8;
    }
}

final class Sergeant extends DynamicPiece{
    protected Sergeant() {
        this.setNamePiece("sergeant");
        this.rank = 7;
    }
}

final class Lieutenant extends DynamicPiece{
    protected Lieutenant() {
        this.setNamePiece("lieutenant");
        this.rank = 6;
    }
}

final class Captain extends DynamicPiece{
    protected Captain() {
        this.setNamePiece("captain");
        this.rank = 5;
    }
}

final class Major extends DynamicPiece{
    protected Major() {
        this.setNamePiece("major");
        this.rank = 4;
    }
}

final class Colonel extends DynamicPiece{
    protected Colonel() {
        this.setNamePiece("colonel");
        this.rank = 3;
    }
}

final class General extends DynamicPiece{
    protected General() {
        this.setNamePiece("general");
        this.rank = 2;
    }
}

final class Flag extends StaticPiece {

    private boolean captured;

    protected Flag() {
        this.setNamePiece("flag");
        this.captured = false;
    }

    protected boolean isCaptured() {
        return this.captured;
    }

    protected void beCaptured() {
        this.captured = true;
    }
}

final class Bomb extends StaticPiece {

    protected Bomb() {
        this.setNamePiece("bomb");
    }
}
