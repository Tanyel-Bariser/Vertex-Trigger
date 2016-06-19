package com.vertextrigger.inanimate.portal;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.assets.AudioManager;

public enum PortalTeleportation {
	MOVING_OPPOSITE_VERTICAL_DIRECTION {
		@Override
		public void setLinearVelocity(final Body body) {
			body.setLinearVelocity(body.getLinearVelocity().x, -body.getLinearVelocity().y);
		}
	},

	MOVING_OPPOSITE_HORIZONTAL_DIRECTION {
		@Override
		public void setLinearVelocity(final Body body) {
			body.setLinearVelocity(-body.getLinearVelocity().x, body.getLinearVelocity().y);
		}
	},

	MOVING_SAME_DIRECTION {
		@Override
		public void setLinearVelocity(final Body body) { /* nothing */
		}
	},

	MOVING_DIFFERENT_XY_AXIS_DIRECTION {
		@Override
		public void setLinearVelocity(final Body body) {
			body.setLinearVelocity(body.getLinearVelocity().y, body.getLinearVelocity().x);
		}
	};

	public void teleport(final Body body, final Vector2 pairPortalPosition) {
		((Teleportable) body.getUserData()).setNewPositionFromPortal(pairPortalPosition);
		AudioManager.playPortalSound();
		setLinearVelocity(body);
	}

	abstract void setLinearVelocity(final Body body);
}