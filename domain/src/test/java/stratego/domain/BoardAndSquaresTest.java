package stratego.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardAndSquaresTest {

    public void initializeForTesting(Board board) {
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                if ((i == 1 && j == 1) || (i == 10 && j == 10)) {
                    board.getSquare(i, j).updatePiece(new Flag());
                } else if (j > 4 && j < 7) {
                    board.getSquare(i, j).updatePiece(null);
                } else {
                    board.getSquare(i, j).updatePiece(new Marshal());
                }
            }
        }
    }

    public void initializeForTestingEmptyBoard(Board board) {
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                board.getSquare(i, j).updatePiece(null);
            }
        }
    }

    public void initializeForTestingBoardWithWater(Board board) {
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                if ((i == 1 && j == 1) || (i == 10 && j == 10)) {
                    board.getSquare(i, j).updatePiece(new Flag());
                } else if (j > 4 && j < 7) {
                    board.getSquare(i, j).updatePiece(null);
                    if ((i == 3 || i == 4 || i == 7 || i == 8) && (j == 5 || j == 6)) {
                        board.getSquare(i, j).turnInWater();
                    }
                } else {
                    board.getSquare(i, j).updatePiece(new Marshal());
                }
            }
        }
    }

    private void assignPlayersToPieces(Board board) {
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                if (j < 5) {
                    board.getSquare(i, j).getPieceFromSquare().assignPlayer(board.getPlayer().getOpponent());
                }
                if (j > 6) {
                    board.getSquare(i, j).getPieceFromSquare().assignPlayer(board.getPlayer());
                }
            }
        }
    }

    @Test
    public void aBoardThatIsCreatedShouldNotBeNull() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        assertNotNull(board);
    }

    @Test
    public void aBoardShouldBeAbleToReturnTheSquareWithCoordinates3By3() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        initializeForTesting(board);
        Square squareWithCoordinates3by3 = board.getSquare(3, 3);
        assertNotSame(squareWithCoordinates3by3, board.getSquare(2, 2));
        assertEquals(squareWithCoordinates3by3.getXCoordinate(), 3);
        assertEquals(squareWithCoordinates3by3.getYCoordinate(), 3);
    }

    @Test
    public void aBoardShouldBeAbleToReturnTheFirstSquareWithCoordinates1By1() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        initializeForTesting(board);
        Square squareWithCoordinates1by1 = board.getSquare(1, 1);
        assertNotSame(squareWithCoordinates1by1, board.getSquare(2, 2));
        assertEquals(squareWithCoordinates1by1.getXCoordinate(), 1);
        assertEquals(squareWithCoordinates1by1.getYCoordinate(), 1);
    }

    @Test
    public void aBoardShouldBeAbleToReturnTheFirstSquareWithCoordinates10By10() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        initializeForTesting(board);
        Square squareWithCoordinates10by10 = board.getSquare(10, 10);
        assertNotSame(squareWithCoordinates10by10, board.getSquare(2, 2));
        assertEquals(squareWithCoordinates10by10.getXCoordinate(), 10);
        assertEquals(squareWithCoordinates10by10.getYCoordinate(), 10);
    }

    @Test
    public void aSquareShouldBeAbleToReturnAPieceWithNameMarshal() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        initializeForTesting(board);
        Square squareWithCoordinates9by9 = board.getSquare(9, 9);
        assertEquals(squareWithCoordinates9by9.getPieceFromSquare().getNamePiece(), "marshal");
    }

    @Test
    public void aSquareShouldBeAbleToReturnNoPiece() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        initializeForTesting(board);
        Square squareWithCoordinates5by5 = board.getSquare(5, 5);
        assertNull(squareWithCoordinates5by5.getPieceFromSquare());
    }

    @Test
    public void aSquareShouldBeAbleToUpdateASquareWithPieceNameMarshal() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        initializeForTesting(board);
        Square squareWithCoordinates5by5 = board.getSquare(5, 5);
        squareWithCoordinates5by5.updatePiece(new Marshal());
        assertEquals(squareWithCoordinates5by5.getPieceFromSquare().getNamePiece(), "marshal");
    }

    @Test
    public void aSquareShouldBeAbleToReturnAPieceWithNameFlag() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        initializeForTesting(board);
        assignPlayersToPieces(board);
        Square squareWithCoordinates10by10 = board.getSquare(10, 10);
        assertEquals(squareWithCoordinates10by10.getPieceFromSquare().getNamePiece(), "flag");
    }

    @Test
    public void TestIfMarshalCanDo1Step() throws InvalidMoveException {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        initializeForTesting(board);
        assignPlayersToPieces(board);

        Square fromSquare = board.getSquare(1, 7);
        Square toSquare = board.getSquare(1, 6);

        assertEquals(fromSquare.getPieceFromSquare().getNamePiece(), "marshal");
        assertNull(toSquare.getPieceFromSquare());

        board.doMove(fromSquare, toSquare);
        assertNull(fromSquare.getPieceFromSquare());
        assertEquals(toSquare.getPieceFromSquare().getNamePiece(), "marshal");
    }

    @Test
    public void AnotherTestToCheckWhatPieceIsOnSquare1by1() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        initializeForTesting(board);
        Square square1By1 = board.getSquare(1, 1);
        assertEquals("flag", square1By1.getPieceFromSquare().getNamePiece());
    }

    @Test
    public void AnotherTestToCheckWhatPieceIsOnSquare5by1() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        initializeForTesting(board);
        Square square5By1 = board.getSquare(5, 1);
        assertEquals("marshal", square5By1.getPieceFromSquare().getNamePiece());
    }

    @Test
    public void AnotherTestToCheckWhatPieceIsOnSquare1by5() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        initializeForTesting(board);
        Square square1By5 = board.getSquare(1, 5);
        assertNull(square1By5.getPieceFromSquare());
    }

    @Test
    public void testIfBoardIsClearedAferFall() throws InvalidMoveException {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        Player player = new Player();
        initializeForTesting(board);
        assignPlayersToPieces(board);

        board.getSquare(1, 6).updatePiece(new Marshal());
        board.getSquare(1, 6).getPieceFromSquare().assignPlayer(player.getOpponent());

        board.doMove(board.getSquare(1, 7), board.getSquare(1, 6));
        assertNull(board.getSquare(1, 7).getPieceFromSquare());
        assertNull(board.getSquare(1, 6).getPieceFromSquare());
    }

    @Test
    public void testIfMarshalCannotBePlayedIfDistanceIsNotCorrect() {

        InvalidMoveException thrown = Assertions.assertThrows(InvalidMoveException.class, () -> {
            BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
            initializeForTesting(board);
            board.doMove(board.getSquare(1, 3), board.getSquare(1, 5));
        });

        Assertions.assertEquals("the direction or distance the piece has to cover is not allowed", thrown.getMessage());
    }

    @Test
    public void testIfMarshalCannotBePlayedIfDistanceIsDiagonal() {
        InvalidMoveException thrown = Assertions.assertThrows(InvalidMoveException.class, () -> {
            BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
            initializeForTesting(board);
            board.doMove(board.getSquare(4, 4), board.getSquare(5, 5));
        });

        Assertions.assertEquals("the direction or distance the piece has to cover is not allowed", thrown.getMessage());
    }

    @Test
    public void testIfMarshalCannotBePlayedIfSquareIsChosenTwoTimes() {
        InvalidMoveException thrown = Assertions.assertThrows(InvalidMoveException.class, () -> {
            BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
            initializeForTesting(board);
            board.doMove(board.getSquare(4, 4), board.getSquare(4, 4));
        });

        Assertions.assertEquals("the direction or distance the piece has to cover is not allowed", thrown.getMessage());
    }

    @Test
    public void testIfAnEmptySquareIsNotAllowedToPlay() {
        InvalidMoveException thrown = Assertions.assertThrows(InvalidMoveException.class, () -> {
            BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
            initializeForTesting(board);
            board.doMove(board.getSquare(5, 5), board.getSquare(5, 6));
        });

        Assertions.assertEquals("this square does not contain a piece", thrown.getMessage());
    }

    @Test
    public void testIfFlagCannotBePlayed() {
        InvalidMoveException thrown = Assertions.assertThrows(InvalidMoveException.class, () -> {
            BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
            initializeForTesting(board);
            board.doMove(board.getSquare(1, 1), board.getSquare(1, 2));
        });

        Assertions.assertEquals("this piece is not allowed to move", thrown.getMessage());
    }

    @Test
    public void testIfPieceIsAssignedByRightPlayerDuringInitialization() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        initializeForTesting(board);
        assignPlayersToPieces(board);
        Player otherPlayer = board.getSquare(7, 7).getPieceFromSquare().getPlayer();
        Player player = board.getSquare(1, 1).getPieceFromSquare().getPlayer();
        assertEquals(player.getNamePlayer(), "two");
        assertEquals(otherPlayer.getNamePlayer(), "one");
    }

    @Test
    public void testIfPlayersSwitchTurnAfterAMove() throws InvalidMoveException {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        initializeForTesting(board);
        assignPlayersToPieces(board);
        Square fromSquare = board.getSquare(2, 7);
        Square toSquare = board.getSquare(2, 6);
        assertEquals(board.getPlayer().hasTurn(), true);
        assertEquals(board.getOpponent().hasTurn(), false);
        board.doMove(fromSquare, toSquare);
        assertEquals(board.getPlayer().hasTurn(), false);
        assertEquals(board.getOpponent().hasTurn(), true);
    }

    @Test
    public void testIfMoveCannotBeDoneIfPlayerHasNotTurn() throws InvalidMoveException {
        InvalidMoveException thrown = Assertions.assertThrows(InvalidMoveException.class, () -> {
            BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
            initializeForTesting(board);
            assignPlayersToPieces(board);
            Square fromSquare = board.getSquare(1, 4); // square that contains a piece from opponent
            Square toSquare = board.getSquare(1, 5);

            board.doMove(fromSquare, toSquare);
        });

        Assertions.assertEquals("the attacking piece does not belong to player that has turn", thrown.getMessage());

    }

    @Test
    public void testIfPieceCannotAttackPieceFromSamePlayer() throws InvalidMoveException {
        InvalidMoveException thrown = Assertions.assertThrows(InvalidMoveException.class, () -> {
            BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
            initializeForTesting(board);
            assignPlayersToPieces(board);
            Square fromSquare = board.getSquare(1, 8);
            Square toSquare = board.getSquare(1, 7);

            board.doMove(fromSquare, toSquare);
        });

        Assertions.assertEquals("player attacks its own piece", thrown.getMessage());
    }

    @Test
    public void testIfSquareReturnsTrueForDynamicPieceIfItHasMarshal() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        initializeForTesting(board);
        assignPlayersToPieces(board);
        Square squareThatHasMarshal = board.getSquare(3, 3);
        assertEquals(true, squareThatHasMarshal.hasDynamicPiece());
    }

    @Test
    public void testIfSquareReturnsFalseForDynamicPieceIfItHasFlag() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        initializeForTesting(board);
        assignPlayersToPieces(board);
        Square squareThatHasFlag = board.getSquare(1, 1);
        assertEquals(false, squareThatHasFlag.hasDynamicPiece());
    }

    @Test
    public void testIfSquareReturnsFalseForCapturedFlagIfFlagIsStillInGame() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        initializeForTesting(board);
        assignPlayersToPieces(board);
        Square squareThatHasFlag = board.getSquare(1, 1);
        assertEquals(false, squareThatHasFlag.hasCapturedFlag());
    }

    @Test
    public void testIfSquareReturnsTrueForCapturedFlagIfFlagIsStolen() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        initializeForTesting(board);
        assignPlayersToPieces(board);
        Square squareThatHasFlag = board.getSquare(1, 1);
        ((Flag) (squareThatHasFlag.getPieceFromSquare())).beCaptured();
        assertEquals(true, squareThatHasFlag.hasCapturedFlag());
    }

    @Test
    public void testIfBoardReturnsFalseForGameHasEndedIfFlagHasNotBeenStolenYet() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        initializeForTesting(board);
        assignPlayersToPieces(board);
        assertEquals(false, board.hasGameEnded());
    }

    @Test
    public void testIfBoardReturnsTrueForGameHasEndedIfFlagHasBeenCaptured() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        initializeForTesting(board);
        assignPlayersToPieces(board);
        Square squareThatHasFlag = board.getSquare(1, 1);
        ((Flag) (squareThatHasFlag.getPieceFromSquare())).beCaptured();
        assertEquals(true, board.hasGameEnded());
    }

    @Test
    public void testIfBoardReturnsTrueForGameHasEndedIfOtherPlayerDoesNotHaveDynamicPiecesAnymore() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        initializeForTesting(board);
        assignPlayersToPieces(board);

        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                if (board.getSquare(i, j).getPieceFromSquare() instanceof Flag) {
                } else {
                    board.getSquare(i, j).deletePiece();
                }
            }
        }
        assertEquals(true, board.hasGameEnded());
    }

    @Test
    public void testIfGameHasBegunAfterFirstMove() throws InvalidMoveException {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        initializeForTesting(board);
        assignPlayersToPieces(board);
        assertEquals(false, board.hasGameBegun());
        board.doMove(board.getSquare(1, 7), board.getSquare(1, 6));
        assertEquals(true, board.hasGameBegun());
    }

    @Test
    public void testIfBoardCanReturnTheFlagOfFirstPlayer() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        initializeForTesting(board);
        assignPlayersToPieces(board);
        assertInstanceOf(Flag.class, board.getSquareWithFlag(board.getPlayer()).getPieceFromSquare());
    }

    @Test
    public void testIfBoardCanReturnNullIfInputPlayerIsNotCorrect() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        initializeForTesting(board);
        assignPlayersToPieces(board);
        assertNull(board.getSquareWithFlag(new Player()));
    }

    @Test
    public void testIfGameEndsWhenSpyEncountersFlag() throws InvalidMoveException {
        Spy spy = new Spy();
        Flag flag = new Flag();
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        Player player = new Player();
        initializeForTesting(board);
        assignPlayersToPieces(board);
        board.getSquare(5, 5).updatePiece(flag);
        board.getSquare(5, 6).updatePiece(spy);
        Square fromSquare = board.getSquare(5, 6);
        fromSquare.getPieceFromSquare().assignPlayer(player);
        Square toSquare = board.getSquare(5, 5);
        toSquare.getPieceFromSquare().assignPlayer(player.getOpponent());
        board.doMove(fromSquare, toSquare);
        assertEquals(board.hasGameEnded(), true);
    }

    @Test
    public void testIfSpyWinsWhenItEncountersMarshal() throws InvalidMoveException {
        Spy spy = new Spy();
        Marshal marshal = new Marshal();
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        Player player = new Player();
        initializeForTesting(board);
        assignPlayersToPieces(board);
        board.getSquare(5, 5).updatePiece(marshal);
        board.getSquare(5, 6).updatePiece(spy);
        Square fromSquare = board.getSquare(5, 6);
        fromSquare.getPieceFromSquare().assignPlayer(player);
        Square toSquare = board.getSquare(5, 5);
        toSquare.getPieceFromSquare().assignPlayer(player.getOpponent());
        board.doMove(fromSquare, toSquare);
        assertNull(board.getSquare(5, 6).getPieceFromSquare());
        assertInstanceOf(Spy.class, board.getSquare(5, 5).getPieceFromSquare());
        assertEquals(true, board.getSquare(5, 5).getPieceFromSquare().isActive());
    }

    @Test
    public void testIfSpyWinsWhenMarshalEncountersSpy() throws InvalidMoveException {
        Spy spy = new Spy();
        Marshal marshal = new Marshal();
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        Player player = new Player();
        initializeForTesting(board);
        assignPlayersToPieces(board);
        board.getSquare(5, 6).updatePiece(marshal);
        board.getSquare(5, 5).updatePiece(spy);
        Square fromSquare = board.getSquare(5, 6);
        fromSquare.getPieceFromSquare().assignPlayer(player);
        Square toSquare = board.getSquare(5, 5);
        toSquare.getPieceFromSquare().assignPlayer(player.getOpponent());
        board.doMove(fromSquare, toSquare);
        assertNull(board.getSquare(5, 6).getPieceFromSquare());
        assertInstanceOf(Spy.class, board.getSquare(5, 5).getPieceFromSquare());
        assertEquals(true, board.getSquare(5, 5).getPieceFromSquare().isActive());
    }

    @Test
    public void testIfScoutWinsWhenScoutEncountersSpy() throws InvalidMoveException {
        Spy spy = new Spy();
        Scout scout = new Scout();
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        Player player = new Player();
        initializeForTesting(board);
        assignPlayersToPieces(board);
        board.getSquare(5, 6).updatePiece(scout);
        board.getSquare(5, 5).updatePiece(spy);
        Square fromSquare = board.getSquare(5, 6);
        fromSquare.getPieceFromSquare().assignPlayer(player);
        Square toSquare = board.getSquare(5, 5);
        toSquare.getPieceFromSquare().assignPlayer(player.getOpponent());
        board.doMove(fromSquare, toSquare);
        assertNull(board.getSquare(5, 6).getPieceFromSquare());
        assertInstanceOf(Scout.class, board.getSquare(5, 5).getPieceFromSquare());
        assertEquals(true, board.getSquare(5, 5).getPieceFromSquare().isActive());
    }

    @Test
    public void testIfMarshalWinsWhenMarshalEncountersScout() throws InvalidMoveException {
        Scout scout = new Scout();
        Marshal marshal = new Marshal();
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        Player player = new Player();
        initializeForTesting(board);
        assignPlayersToPieces(board);
        board.getSquare(5, 6).updatePiece(marshal);
        board.getSquare(5, 5).updatePiece(scout);
        Square fromSquare = board.getSquare(5, 6);
        fromSquare.getPieceFromSquare().assignPlayer(player);
        Square toSquare = board.getSquare(5, 5);
        toSquare.getPieceFromSquare().assignPlayer(player.getOpponent());
        board.doMove(fromSquare, toSquare);
        assertNull(board.getSquare(5, 6).getPieceFromSquare());
        assertInstanceOf(Marshal.class, board.getSquare(5, 5).getPieceFromSquare());
        assertEquals(true, board.getSquare(5, 5).getPieceFromSquare().isActive());
    }

    @Test
    public void TestIfMarshalCanAttackOtherMarshalAndIfTheyBothGetInactiveAfter() throws InvalidMoveException {
        Marshal marshal2 = new Marshal();
        Marshal marshal = new Marshal();
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        Player player = new Player();
        initializeForTesting(board);
        assignPlayersToPieces(board);
        board.getSquare(5, 6).updatePiece(marshal);
        board.getSquare(5, 5).updatePiece(marshal2);
        Square fromSquare = board.getSquare(5, 6);
        fromSquare.getPieceFromSquare().assignPlayer(player);
        Square toSquare = board.getSquare(5, 5);
        toSquare.getPieceFromSquare().assignPlayer(player.getOpponent());
        board.doMove(fromSquare, toSquare);
        assertNull(board.getSquare(5, 6).getPieceFromSquare());
        assertNull(board.getSquare(5,5).getPieceFromSquare());
        assertEquals(false, marshal2.isActive());
        assertEquals(false, marshal.isActive());
    }

    @Test
    public void testIfScoutIsAllowedToDoMultipleSteps() throws InvalidMoveException {
        Scout scout = new Scout();
        Flag flag = new Flag();
        Flag flag2 = new Flag();
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        Player player = new Player();

        initializeForTestingEmptyBoard(board);
        board.getSquare(1, 1).updatePiece(scout);
        board.getSquare(3, 2).updatePiece(flag);
        board.getSquare(10, 10).updatePiece(flag2);

        board.getSquare(1, 1).getPieceFromSquare().assignPlayer(player);
        board.getSquare(3, 2).getPieceFromSquare().assignPlayer(player);
        board.getSquare(10, 10).getPieceFromSquare().assignPlayer(player.getOpponent());

        Square fromSquare = board.getSquare(1, 1);
        Square toSquare = board.getSquare(1, 10);

        board.doMove(fromSquare, toSquare);
        assertNull(board.getSquare(1, 1).getPieceFromSquare());
        assertInstanceOf(Scout.class, board.getSquare(1, 10).getPieceFromSquare());
    }

    @Test
    public void testIfScoutIsNotAllowedToJumpOverOtherPieces() throws InvalidMoveException {
        InvalidMoveException thrown = Assertions.assertThrows(InvalidMoveException.class, () -> {
            Scout scout = new Scout();
            Flag flag = new Flag();
            Flag flag2 = new Flag();
            BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
            Player player = new Player();

            initializeForTestingEmptyBoard(board);
            board.getSquare(1, 1).updatePiece(scout);
            board.getSquare(1, 2).updatePiece(flag);
            board.getSquare(10, 10).updatePiece(flag2);

            board.getSquare(1, 1).getPieceFromSquare().assignPlayer(player);
            board.getSquare(1, 2).getPieceFromSquare().assignPlayer(player);
            board.getSquare(10, 10).getPieceFromSquare().assignPlayer(player.getOpponent());

            Square fromSquare = board.getSquare(1, 1);
            Square toSquare = board.getSquare(1, 10);

            board.doMove(fromSquare, toSquare);
        });

        Assertions.assertEquals("a scout is not allowed to jump over pieces or water", thrown.getMessage());
    }

    @Test
    public void testIfYouAreNotAllowedToMakeTheSameMove5TimesInARow() throws InvalidMoveException {
        InvalidMoveException thrown = Assertions.assertThrows(InvalidMoveException.class, () -> {
            Scout scout = new Scout();
            Scout scout2 = new Scout();
            Flag flag = new Flag();
            Flag flag2 = new Flag();
            BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();

            initializeForTestingEmptyBoard(board);
            board.getSquare(1, 1).updatePiece(scout);
            board.getSquare(1, 2).updatePiece(flag);
            board.getSquare(9, 9).updatePiece(scout2);
            board.getSquare(10, 10).updatePiece(flag2);

            board.getSquare(1, 1).getPieceFromSquare().assignPlayer(board.getPlayer());
            board.getSquare(1, 2).getPieceFromSquare().assignPlayer(board.getPlayer());
            board.getSquare(9, 9).getPieceFromSquare().assignPlayer(board.getPlayer().getOpponent());
            board.getSquare(10, 10).getPieceFromSquare().assignPlayer(board.getPlayer().getOpponent());

            board.doMove(board.getSquare(1, 1), board.getSquare(2, 1));
            board.doMove(board.getSquare(9, 9), board.getSquare(8, 9));
            board.doMove(board.getSquare(2, 1), board.getSquare(1, 1)); // 1
            board.doMove(board.getSquare(8, 9), board.getSquare(9, 9));
            board.doMove(board.getSquare(1, 1), board.getSquare(2, 1));
            board.doMove(board.getSquare(9, 9), board.getSquare(8, 9));
            board.doMove(board.getSquare(2, 1), board.getSquare(1, 1)); // 2
            board.doMove(board.getSquare(8, 9), board.getSquare(9, 9));
            board.doMove(board.getSquare(1, 1), board.getSquare(2, 1));
            board.doMove(board.getSquare(9, 9), board.getSquare(8, 9));
            board.doMove(board.getSquare(2, 1), board.getSquare(1, 1)); // 3
            board.doMove(board.getSquare(8, 9), board.getSquare(9, 9));
            board.doMove(board.getSquare(1, 1), board.getSquare(2, 1));
            board.doMove(board.getSquare(9, 9), board.getSquare(8, 9));
            board.doMove(board.getSquare(2, 1), board.getSquare(1, 1)); // 4
            board.doMove(board.getSquare(8, 9), board.getSquare(9, 9));
            board.doMove(board.getSquare(1, 1), board.getSquare(2, 1));
            board.doMove(board.getSquare(9, 9), board.getSquare(8, 9));
            board.doMove(board.getSquare(2, 1), board.getSquare(1, 1)); // 5
        });

        Assertions.assertEquals("it is not allowed to do the same move five times in a row", thrown.getMessage());

    }

    @Test
    public void testIfOpponentIsNotAllowedToMakeTheSameMove5TimesInARow() throws InvalidMoveException {
        InvalidMoveException thrown = Assertions.assertThrows(InvalidMoveException.class, () -> {
            Scout scout = new Scout();
            Scout scout2 = new Scout();
            Flag flag = new Flag();
            Flag flag2 = new Flag();
            BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();

            initializeForTestingEmptyBoard(board);
            board.getSquare(1, 1).updatePiece(scout);
            board.getSquare(1, 2).updatePiece(flag);
            board.getSquare(9, 9).updatePiece(scout2);
            board.getSquare(10, 10).updatePiece(flag2);

            board.getSquare(1, 1).getPieceFromSquare().assignPlayer(board.getPlayer());
            board.getSquare(1, 2).getPieceFromSquare().assignPlayer(board.getPlayer());
            board.getSquare(9, 9).getPieceFromSquare().assignPlayer(board.getPlayer().getOpponent());
            board.getSquare(10, 10).getPieceFromSquare().assignPlayer(board.getPlayer().getOpponent());

            board.doMove(board.getSquare(1, 1), board.getSquare(2, 1));
            board.doMove(board.getSquare(9, 9), board.getSquare(8, 9));
            board.doMove(board.getSquare(2, 1), board.getSquare(1, 1)); // 1
            board.doMove(board.getSquare(8, 9), board.getSquare(9, 9)); // 1 opponent
            board.doMove(board.getSquare(1, 1), board.getSquare(2, 1));
            board.doMove(board.getSquare(9, 9), board.getSquare(8, 9));
            board.doMove(board.getSquare(2, 1), board.getSquare(1, 1)); // 2
            board.doMove(board.getSquare(8, 9), board.getSquare(9, 9)); // 2 opponent
            board.doMove(board.getSquare(1, 1), board.getSquare(2, 1));
            board.doMove(board.getSquare(9, 9), board.getSquare(8, 9));
            board.doMove(board.getSquare(2, 1), board.getSquare(1, 1)); // 3
            board.doMove(board.getSquare(8, 9), board.getSquare(9, 9)); // 3 opponent
            board.doMove(board.getSquare(1, 1), board.getSquare(2, 1));
            board.doMove(board.getSquare(9, 9), board.getSquare(8, 9));
            board.doMove(board.getSquare(2, 1), board.getSquare(3, 1));
            board.doMove(board.getSquare(8, 9), board.getSquare(9, 9)); // 4 opponent
            board.doMove(board.getSquare(3, 1), board.getSquare(2, 1));
            board.doMove(board.getSquare(9, 9), board.getSquare(8, 9));
            board.doMove(board.getSquare(2, 1), board.getSquare(1, 1)); // 1
            board.doMove(board.getSquare(8, 9), board.getSquare(9, 9)); // 5 opponent
        });

        Assertions.assertEquals("it is not allowed to do the same move five times in a row", thrown.getMessage());

    }

    @Test
    public void testIfConsecutiveCountsIsNotCountWhenDifferentMovesAreMade() throws InvalidMoveException {
        Scout scout = new Scout();
        Scout scout2 = new Scout();
        Flag flag = new Flag();
        Flag flag2 = new Flag();
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();

        initializeForTestingEmptyBoard(board);
        board.getSquare(1, 1).updatePiece(scout);
        board.getSquare(1, 2).updatePiece(flag);
        board.getSquare(9, 9).updatePiece(scout2);
        board.getSquare(10, 10).updatePiece(flag2);

        board.getSquare(1, 1).getPieceFromSquare().assignPlayer(board.getPlayer());
        board.getSquare(1, 2).getPieceFromSquare().assignPlayer(board.getPlayer());
        board.getSquare(9, 9).getPieceFromSquare().assignPlayer(board.getPlayer().getOpponent());
        board.getSquare(10, 10).getPieceFromSquare().assignPlayer(board.getPlayer().getOpponent());

        board.doMove(board.getSquare(1, 1), board.getSquare(2, 1));
        board.doMove(board.getSquare(9, 9), board.getSquare(8, 9));
        board.doMove(board.getSquare(2, 1), board.getSquare(1, 1)); // 1
        board.doMove(board.getSquare(8, 9), board.getSquare(9, 9)); // 1 opponent
        board.doMove(board.getSquare(1, 1), board.getSquare(5, 1)); // different move forwards
        board.doMove(board.getSquare(9, 9), board.getSquare(8, 9));
        board.doMove(board.getSquare(5, 1), board.getSquare(1, 1)); // same move backwards
        board.doMove(board.getSquare(8, 9), board.getSquare(9, 9)); // 2 opponent
        board.doMove(board.getSquare(1, 1), board.getSquare(2, 1));
        board.doMove(board.getSquare(9, 9), board.getSquare(8, 9));
        board.doMove(board.getSquare(2, 1), board.getSquare(1, 1)); // 1
        board.doMove(board.getSquare(8, 9), board.getSquare(9, 9)); // 3 opponent
        board.doMove(board.getSquare(1, 1), board.getSquare(2, 1));
        board.doMove(board.getSquare(9, 9), board.getSquare(8, 9));
        board.doMove(board.getSquare(2, 1), board.getSquare(3, 1));
        board.doMove(board.getSquare(8, 9), board.getSquare(9, 9)); // 4 opponent
        board.doMove(board.getSquare(3, 1), board.getSquare(2, 1));
    }

    @Test
    public void testIfPlayerIsAllowedToDoTheSameMoveTwoTimesAndIfItsConsecutiveMovesIsReset()
            throws InvalidMoveException {
        Scout scout = new Scout();
        Scout scout2 = new Scout();
        Flag flag = new Flag();
        Flag flag2 = new Flag();
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();

        initializeForTestingEmptyBoard(board);
        board.getSquare(1, 1).updatePiece(scout);
        board.getSquare(1, 2).updatePiece(flag);
        board.getSquare(9, 9).updatePiece(scout2);
        board.getSquare(10, 10).updatePiece(flag2);

        board.getSquare(1, 1).getPieceFromSquare().assignPlayer(board.getPlayer());
        board.getSquare(1, 2).getPieceFromSquare().assignPlayer(board.getPlayer());
        board.getSquare(9, 9).getPieceFromSquare().assignPlayer(board.getPlayer().getOpponent());
        board.getSquare(10, 10).getPieceFromSquare().assignPlayer(board.getPlayer().getOpponent());

        board.doMove(board.getSquare(1, 1), board.getSquare(2, 1));
        board.doMove(board.getSquare(9, 9), board.getSquare(8, 9));
        board.doMove(board.getSquare(2, 1), board.getSquare(1, 1)); // 1
        board.doMove(board.getSquare(8, 9), board.getSquare(9, 9)); // 1 opponent
        board.doMove(board.getSquare(1, 1), board.getSquare(2, 1));
        board.doMove(board.getSquare(9, 9), board.getSquare(8, 9));
        board.doMove(board.getSquare(2, 1), board.getSquare(1, 1)); // 2
        board.doMove(board.getSquare(8, 9), board.getSquare(9, 9)); // 2 opponent
        board.doMove(board.getSquare(1, 1), board.getSquare(5, 1));
        board.doMove(board.getSquare(9, 9), board.getSquare(8, 9));
        board.doMove(board.getSquare(5, 1), board.getSquare(1, 1)); // different move 1
        board.doMove(board.getSquare(8, 9), board.getSquare(9, 9)); // 3 opponent
        board.doMove(board.getSquare(1, 1), board.getSquare(6, 1)); // different move 1
        board.doMove(board.getSquare(9, 9), board.getSquare(8, 9));
        board.doMove(board.getSquare(6, 1), board.getSquare(7, 1)); // different move 1
        board.doMove(board.getSquare(8, 9), board.getSquare(9, 9)); // 4 opponent
        board.doMove(board.getSquare(7, 1), board.getSquare(2, 1)); // different move 1
    }

    @Test
    public void testIfInTheBeginningPlayerOneCanBeReturnedAsThePlayerThatHasTurn() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        assertEquals(board.getPlayer(), board.getPlayerThatHasTurn());
    }

    @Test
    public void testIfOpponentCanBeReturnedAsThePlayerThatHasTurnAfterMove() throws InvalidMoveException {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        initializeForTesting(board);
        assignPlayersToPieces(board);

        Square fromSquare = board.getSquare(1, 7);
        Square toSquare = board.getSquare(1, 6);

        assertEquals(fromSquare.getPieceFromSquare().getNamePiece(), "marshal");
        assertNull(toSquare.getPieceFromSquare());

        board.doMove(fromSquare, toSquare);

        assertEquals(board.getOpponent(), board.getPlayerThatHasTurn());
    }

    @Test
    public void testIfBombWinsIfMarshalEncountersIt() throws InvalidMoveException {
        Bomb bomb = new Bomb();
        Marshal marshal = new Marshal();
        Flag flag = new Flag();
        Flag flag2 = new Flag();
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();

        initializeForTestingEmptyBoard(board);
        board.getSquare(1, 7).updatePiece(marshal);
        board.getSquare(1, 6).updatePiece(flag);
        board.getSquare(1, 6).updatePiece(bomb);
        board.getSquare(10, 10).updatePiece(flag2);

        board.getSquare(1, 7).getPieceFromSquare().assignPlayer(board.getPlayer());
        board.getSquare(1, 6).getPieceFromSquare().assignPlayer(board.getPlayer());
        board.getSquare(1, 6).getPieceFromSquare().assignPlayer(board.getPlayer().getOpponent());
        board.getSquare(10, 10).getPieceFromSquare().assignPlayer(board.getPlayer().getOpponent());

        board.doMove(board.getSquare(1, 7), board.getSquare(1, 6));
        assertEquals("bomb", board.getSquare(1, 6).getPieceFromSquare().getNamePiece());
        assertNull(board.getSquare(1, 7).getPieceFromSquare());
    }

    @Test
    public void testIfMinerCanWinIfItEncountersBomb() throws InvalidMoveException {
        Miner miner = new Miner();
        Bomb bomb = new Bomb();
        Flag flag = new Flag();
        Flag flag2 = new Flag();
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();

        initializeForTestingEmptyBoard(board);
        board.getSquare(1, 7).updatePiece(miner);
        board.getSquare(1, 6).updatePiece(flag);
        board.getSquare(1, 6).updatePiece(bomb);
        board.getSquare(10, 10).updatePiece(flag2);

        board.getSquare(1, 7).getPieceFromSquare().assignPlayer(board.getPlayer());
        board.getSquare(1, 6).getPieceFromSquare().assignPlayer(board.getPlayer());
        board.getSquare(1, 6).getPieceFromSquare().assignPlayer(board.getPlayer().getOpponent());
        board.getSquare(10, 10).getPieceFromSquare().assignPlayer(board.getPlayer().getOpponent());

        board.doMove(board.getSquare(1, 7), board.getSquare(1, 6));
        assertEquals("miner", board.getSquare(1, 6).getPieceFromSquare().getNamePiece());
        assertNull(board.getSquare(1, 7).getPieceFromSquare());
    }

    @Test
    public void testIfASquareCanBecomeWater() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        assertEquals(false, board.getSquare(6, 6).isWater());
        board.getSquare(6, 6).turnInWater();
        assertEquals(true, board.getSquare(6, 6).isWater());
    }

    @Test
    public void testIfBoardCanBeInitializedWithWater() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        initializeForTestingBoardWithWater(board);
        assertEquals(true, board.getSquare(3, 5).isWater());
        assertEquals(true, board.getSquare(3, 6).isWater());
        assertEquals(true, board.getSquare(4, 5).isWater());
        assertEquals(true, board.getSquare(4, 6).isWater());
        assertEquals(true, board.getSquare(7, 5).isWater());
        assertEquals(true, board.getSquare(7, 6).isWater());
        assertEquals(true, board.getSquare(8, 5).isWater());
        assertEquals(true, board.getSquare(8, 6).isWater());
        assertEquals(false, board.getSquare(2, 2).isWater());
        assertEquals(false, board.getSquare(8, 8).isWater());
    }

    @Test
    public void testIfScoutIsNotAllowedToJumpOverWater() throws InvalidMoveException {
        InvalidMoveException thrown = Assertions.assertThrows(InvalidMoveException.class, () -> {
            Scout scout = new Scout();
            Flag flag = new Flag();
            Flag flag2 = new Flag();
            BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
            Player player = new Player();

            initializeForTestingBoardWithWater(board);
            board.getSquare(3, 4).updatePiece(scout);
            board.getSquare(1, 2).updatePiece(flag);
            board.getSquare(10, 10).updatePiece(flag2);

            board.getSquare(3, 4).getPieceFromSquare().assignPlayer(player);
            board.getSquare(1, 2).getPieceFromSquare().assignPlayer(player);
            board.getSquare(10, 10).getPieceFromSquare().assignPlayer(player.getOpponent());

            Square fromSquare = board.getSquare(3, 4);
            Square toSquare = board.getSquare(3, 7);

            board.doMove(fromSquare, toSquare);
        });

        Assertions.assertEquals("a scout is not allowed to jump over pieces or water", thrown.getMessage());
    }

    @Test
    public void testIfScoutIsNotAllowedToGoInWater() throws InvalidMoveException {
        InvalidMoveException thrown = Assertions.assertThrows(InvalidMoveException.class, () -> {
            Scout scout = new Scout();
            Flag flag = new Flag();
            Flag flag2 = new Flag();
            BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
            Player player = new Player();

            initializeForTestingBoardWithWater(board);
            board.getSquare(3, 4).updatePiece(scout);
            board.getSquare(1, 2).updatePiece(flag);
            board.getSquare(10, 10).updatePiece(flag2);

            board.getSquare(3, 4).getPieceFromSquare().assignPlayer(player);
            board.getSquare(1, 2).getPieceFromSquare().assignPlayer(player);
            board.getSquare(10, 10).getPieceFromSquare().assignPlayer(player.getOpponent());

            Square fromSquare = board.getSquare(3, 4);
            Square toSquare = board.getSquare(3, 5);

            board.doMove(fromSquare, toSquare);
        });

        Assertions.assertEquals("the piece is not allowed to go in water", thrown.getMessage());
    }

    @Test
    public void testIfAPieceCanBePlaced() throws InvalidPlacementException {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        board.placePiece("Scout", 1, 1, 2);
        assertEquals(board.getSquare(1,1).getPieceFromSquare().getNamePiece(), "scout");
        assertEquals(board.getSquare(1,1).getPieceFromSquare().getPlayer().getId(), 2);
    }

    // @Test
    // public void testIfPieceReturnsBattleWonWhenItHasWon() throws InvalidMoveException {
    //     Bomb bomb = new Bomb();
    //     Marshal marshal = new Marshal();
    //     Scout scout = new Scout();
    //     Flag flag = new Flag();
    //     Flag flag2 = new Flag();
    //     Scout scout2 = new Scout();
    //     BoardInitialization boardInitialization = new BoardInitialization();
        // Board board = boardInitialization.initializeEmptyBoard();

    //     initializeForTestingEmptyBoard(board);
    //     board.getSquare(1, 7).updatePiece(marshal);
    //     board.getSquare(1, 6).updatePiece(flag);
    //     board.getSquare(9,10).updatePiece(scout);
    //     board.getSquare(1, 6).updatePiece(bomb);
    //     board.getSquare(10, 10).updatePiece(flag2);
    //     board.getSquare(8,10).updatePiece(scout2);

    //     board.getSquare(1, 7).getPieceFromSquare().assignPlayer(board.getPlayer());
    //     board.getSquare(1, 6).getPieceFromSquare().assignPlayer(board.getPlayer());
    //     board.getSquare(9, 10).getPieceFromSquare().assignPlayer(board.getPlayer());
    //     board.getSquare(1, 6).getPieceFromSquare().assignPlayer(board.getPlayer().getOpponent());
    //     board.getSquare(10, 10).getPieceFromSquare().assignPlayer(board.getPlayer().getOpponent());
    //     board.getSquare(8, 10).getPieceFromSquare().assignPlayer(board.getPlayer().getOpponent());

    //     assertEquals(false, board.getSquare(1, 6).getPieceFromSquare().hasBattleWon());
    //     board.doMove(board.getSquare(1, 7), board.getSquare(1, 6));
    //     assertEquals("bomb", board.getSquare(1, 6).getPieceFromSquare().getName());
    //     assertEquals(true, board.getSquare(1, 6).getPieceFromSquare().hasBattleWon());
    //     assertNull(board.getSquare(1, 7).getPieceFromSquare());
    //     board.doMove(board.getSquare(8,10), board.getSquare(7,10));
    //     assertEquals(false, board.getSquare(1, 6).getPieceFromSquare().hasBattleWon());
    // }
}

    

    
 









    



    








    
