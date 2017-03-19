package com.vertextrigger.entities;

import com.badlogic.gdx.ai.steer.behaviors.FollowFlowField.FlowField;
import com.badlogic.gdx.math.Vector2;
import com.vertextrigger.ai.Magnet;
import com.vertextrigger.util.PositionConverter;

public class MagnetFlowField implements FlowField<Vector2> {
	private final Vector2[][] field;
	private final int rows, columns;
	private final int containerWidth;
	private final int containerHeight;

	public MagnetFlowField(final int containerWidth, final int containerHeight, final Magnet[] magnets) {
		this.containerWidth = containerWidth;
		this.containerHeight = containerHeight;

		columns = (containerWidth * 2) + 1;
		rows = (containerHeight * 2) + 1;
		field = new Vector2[columns][rows];

		for (int col = 0; col < columns; col++) {
			ROWS: for (int row = 0; row < rows; row++) {
				for (final Magnet magnet : magnets) {
					if (isCloseEnough(col, row, magnet)) {
						// if we are close enough to the magnet, set an attraction force pointing to the centre of magnet
						final Vector2 force = new Vector2(-(col + 0.5f), -(row + 0.5f)).add(magnet.getPosition()).nor();
						field[col][row] = new Vector2(force.x < 0 ? -1 : 1, force.y < 0 ? -1 : 1);
						continue ROWS;
					}
				}
				// set every other index to 0,0
				field[col][row] = new Vector2(0, 0).nor();
			}
		}
	}

	private void prettyPrint() {
		for (int i = field.length - 1; i >= 0; i--) {
			final Vector2[] arr = field[i];
			for (int j = 0; j < arr.length; j++) {
				System.out.print('(' + String.format("%.1f", field[i][j].x) + "," + String.format("%.1f", field[i][j].y) + "), ");
			}
			System.out.println();
		}
	}

	private boolean isCloseEnough(final int column, final int row, final Magnet magnet) {
		return magnet.getPosition().dst(column + .5f, row + .5f) < magnet.getStrength();
	}

	@Override
	public Vector2 lookup(final Vector2 position) {
		final Vector2 flowFieldPosition = PositionConverter.convertPosition(containerWidth, containerHeight, position);
		return field[(int) flowFieldPosition.x][(int) flowFieldPosition.y];
	}
}