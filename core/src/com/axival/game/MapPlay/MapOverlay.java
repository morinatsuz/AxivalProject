package com.axival.game.MapPlay;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.List;

public class MapOverlay {
    private MapScreen screen;
    private Board board;
    private SpriteBatch batch;
    private Texture tile, tile2;
    private List<Vector2> area;
    private LinkedList<Vector2> area2;
    public MapOverlay(MapScreen screen, Board board, SpriteBatch batch) {
        this.screen = screen;
        this.board = board;
        this.batch = batch;
        this.tile = new Texture("map-imgs/bright_ol_walk.png");
        this.tile2 = new Texture("map-imgs/ol_walk.png");
    }

    public void showOverlay(int col, int row, int radius) {
        area = new LinkedList<Vector2>();
        area.addAll(board.getOverlay(col, row, radius));
        for (int y = 0; y < 13; y++) {
            for (int x = 0; x < 24; x++) {
                if (!board.map[y][x].isObstacle()) {
                    if (area.contains(new Vector2(x,y))) {
                        batch.draw(tile, board.map[y][x].corX + 5 , board.map[y][x].corY,
                                tile.getWidth() * 0.95f, tile.getHeight() * 0.95f);
                    }
                    else {
                        batch.draw(tile2, board.map[y][x].corX + 5 , board.map[y][x].corY,
                                tile.getWidth() * 0.95f, tile.getHeight() * 0.95f);
                    }
                }
            }
        }
    }

    public void showOverlay(LinkedList<Vector2> skillOverlay) {
        for (Vector2 vec: skillOverlay) {
            int row = (int)vec.y;
            int col = (int)vec.x;
            if (!board.map[row][col].isObstacle()) {
                batch.draw(tile, board.map[row][col].corX + 5, board.map[row][col].corY,
                        tile.getWidth() * 0.95f, tile.getHeight() * 0.95f);
            }
        }
    }

    public LinkedList<Vector2> avaiableOverlay() {
        area2 = new LinkedList<Vector2>();
        for (int y = 0; y < 13; y++) {
            for (int x = 0; x < 24; x++) {
                if (!board.map[y][x].isObstacle(true)) {
                    area2.add(new Vector2(x, y));
                }
            }
        }
        return area2;
    }
}
