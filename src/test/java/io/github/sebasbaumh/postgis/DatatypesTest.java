/*
 * DatatypesTest.java
 * 
 * PostGIS extension for PostgreSQL JDBC driver - example and test classes
 * 
 * (C) 2004 Paul Ramsey, pramsey@refractions.net
 * 
 * (C) 2005 Markus Schaber, markus.schaber@logix-tt.com
 *
 * (C) 2015 Phillip Ross, phillip.w.g.ross@gmail.com
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 * 
 */

package io.github.sebasbaumh.postgis;

import java.sql.SQLException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatatypesTest extends DatabaseTest
{

	private static final Logger logger = LoggerFactory.getLogger(DatatypesTest.class);

	private static final String mlng_str = "MULTILINESTRING ((10 10 0,20 10 0,20 20 0,20 10 0,10 10 0),(5 5 0,5 6 0,6 6 0,6 5 0,5 5 0))";

	private static final String mplg_str = "MULTIPOLYGON (((10 10 0,20 10 0,20 20 0,20 10 0,10 10 0),(5 5 0,5 6 0,6 6 0,6 5 0,5 5 0)),((10 10 0,20 10 0,20 20 0,20 10 0,10 10 0),(5 5 0,5 6 0,6 6 0,6 5 0,5 5 0)))";

	private static final String plg_str = "POLYGON ((10 10 0,20 10 0,20 20 0,20 10 0,10 10 0),(5 5 0,5 6 0,6 6 0,6 5 0,5 5 0))";

	private static final String lng_str = "LINESTRING  (10 10 20,20 20 20, 50 50 50, 34 34 34)";

	private static final String ptg_str = "POINT(10 10 20)";

	private static final String lr_str = "(10 10 20,34 34 34, 23 19 23 , 10 10 11)";

	private static final String cr_str = "CIRCULARSTRING(-9 2,-8 3,-7 2)";

	private static final String cr_str2 = "CIRCULARSTRING(0 -1,-1 0,0 1,1 0,0 -1)";

	// FIX: add tests here

	@SuppressWarnings("unchecked")
	private <T extends Geometry> T assertGeometry(Class<T> clazz, String wkt) throws SQLException
	{
		Geometry geom = getGeometryFromWKT(wkt);
		if (clazz.isInstance(geom))
		{
			return (T) geom;
		}
		throw new IllegalArgumentException(
				"expected: " + clazz.getCanonicalName() + " got: " + geom.getClass().getCanonicalName());
	}

	@Test
	public void testLinearRing() throws SQLException
	{
		if (!hasDatabase())
		{
			return;
		}
		logger.trace("void testLinearRing()");
		logger.info(lr_str);
		LinearRing lr = assertGeometry(LinearRing.class, lr_str);
		logger.info(lr.toString());
	}

	@Test
	public void testPoint() throws SQLException
	{
		if (!hasDatabase())
		{
			return;
		}
		logger.trace("void testPoint()");
		logger.info(ptg_str);
		Point ptg = assertGeometry(Point.class, ptg_str);
		logger.info(ptg.toString());
	}

	@Test
	public void testLineString() throws SQLException
	{
		if (!hasDatabase())
		{
			return;
		}
		logger.trace("void testLineString()");
		logger.info(lng_str);
		LineString lng = assertGeometry(LineString.class, lng_str);
		logger.info(lng.toString());
	}

	@Test
	public void testCircularString() throws SQLException
	{
		if (!hasDatabase())
		{
			return;
		}
		logger.trace("void testCircularString()");
		logger.info(cr_str);
		CircularString lng = assertGeometry(CircularString.class, cr_str);
		logger.info(lng.toString());
	}

	@Test
	public void testCircularString2() throws SQLException
	{
		if (!hasDatabase())
		{
			return;
		}
		logger.trace("void testCircularString2()");
		logger.info(cr_str2);
		CircularString lng = assertGeometry(CircularString.class, cr_str2);
		logger.info(lng.toString());
	}

	@Test
	public void testPolygon() throws SQLException
	{
		if (!hasDatabase())
		{
			return;
		}
		logger.trace("void testPolygon()");
		logger.info(plg_str);
		Polygon plg = assertGeometry(Polygon.class, plg_str);
		logger.info(plg.toString());
	}

	@Test
	public void testMultiPolygon() throws SQLException
	{
		if (!hasDatabase())
		{
			return;
		}
		logger.trace("void testMultiPolygon()");
		logger.info(mplg_str);
		MultiPolygon mplg = assertGeometry(MultiPolygon.class, mplg_str);
		logger.info(mplg.toString());
	}

	@Test
	public void testMultiLineString() throws SQLException
	{
		if (!hasDatabase())
		{
			return;
		}
		logger.trace("void testMultiLineString()");
		logger.info(mlng_str);
		MultiLineString mlng = assertGeometry(MultiLineString.class, mlng_str);
		logger.info(mlng.toString());
	}

	@Test
	public void testPGgeometry() throws SQLException
	{
		if (!hasDatabase())
		{
			return;
		}
		logger.trace("void testPGgeometry()");
		logger.info(mlng_str);
		PGgeometry pgf = new PGgeometry(getWKBFromWKT(mlng_str));
		logger.info(pgf.toString());
	}

}