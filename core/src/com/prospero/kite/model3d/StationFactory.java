package com.prospero.kite.model3d;

import com.prospero.kite.model.Station;
import com.prospero.kite.model.Station1;
import com.prospero.kite.model.StationType;

public class StationFactory {

	public static Station getStation(StationType type, Station station) {
		switch (type) {
		case Station1:
			return new Station1(station);
		default:
			break;
		}
		return null;
	}
}
