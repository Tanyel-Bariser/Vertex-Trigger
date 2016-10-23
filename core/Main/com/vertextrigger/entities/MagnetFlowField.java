package com.vertextrigger.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.steer.behaviors.FollowFlowField.FlowField;
import com.badlogic.gdx.math.*;

public class MagnetFlowField implements FlowField<Vector2> {
	private static final Vector2 DEFAULT_VECTOR2 = new Vector2(0f, 0f);
	private final Vector2[] magnetPositions;
	Vector2[][] field;
	int rows, columns;
	int resolution;

	public MagnetFlowField(final Vector2[] magnetPositions, final float width, final float height, final int resolution) {
		this.magnetPositions = magnetPositions;
		this.resolution = resolution;
		columns = MathUtils.ceil(width / resolution);
		rows = MathUtils.ceil(height / resolution);
		field = new Vector2[columns][rows];

		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows; j++) {
				field[i][j] = DEFAULT_VECTOR2;
			}
		}
		set(new Vector2(0, 0));
		// prettyPrint();
	}

	private void prettyPrint() {
		final StringBuilder out = new StringBuilder();
		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows; j++) {
				out.append(field[i][j]).append(", ");
			}
			out.append('\n');
		}
		Gdx.app.log("", out.toString());
	}

	private void set(final Vector2 position) {
		final int column = (int) MathUtils.clamp(position.x/* / resolution */, 0, columns - 1);
		final int row = (int) MathUtils.clamp(position.y/* / resolution */, 0, rows - 1);
		field[column][row] = new Vector2(0.1f, 0.1f);
	}

	@Override
	public Vector2 lookup(final Vector2 position) {
		Gdx.app.log("Position", position.toString());
		final int column = (int) MathUtils.clamp(position.x, 0, columns - 1);
		final int row = (int) MathUtils.clamp(position.y, 0, rows - 1);
		return field[column][row];
	}
}