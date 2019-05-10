package io.github.sebasbaumh.postgis;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import io.github.sebasbaumh.postgis.binary.BinaryParser;
import io.github.sebasbaumh.postgis.binary.BinaryWriter;

/**
 * @author Sebastian Baumhekel
 */
@SuppressWarnings("javadoc")
public class ParserTestLocal
{
	/**
	 * Gets a {@link Geometry} from a WKB string in hex format.
	 * @param wkb WKB
	 * @return {@link Geometry}
	 */
	private static Geometry getGeometryFromWKB(String wkb)
	{
		return BinaryParser.parse(wkb);
	}

	/**
	 * Gets a WKB string in hex format from a {@link Geometry}.
	 * @param geom {@link Geometry}
	 * @return WKB string
	 */
	private static String getWKBFromGeometry(Geometry geom)
	{
		return BinaryWriter.writeHexed(geom);
	}

	/**
	 * Tests using an WKB string (hex format) (local).
	 * @param wkb WKB
	 * @throws SQLException
	 */
	private static void testLocal(String wkb)
	{
		testLocal(wkb, true);
	}

	/**
	 * Tests using an WKB string (hex format) (local).
	 * @param wkb WKB
	 * @param compareFullWkbStrings compare the full WKB strings?
	 * @throws SQLException
	 */
	private static void testLocal(String wkb, boolean compareFullWkbStrings)
	{
		// get geometry from WKB and parse it back
		Geometry geom = getGeometryFromWKB(wkb);
		String parsed = getWKBFromGeometry(geom);
		Geometry reparsed = getGeometryFromWKB(parsed);
		// ensure geometries match
		Assert.assertEquals(geom, reparsed);
		// then compare the WKB strings?
		if (compareFullWkbStrings)
		{
			Assert.assertEquals(wkb, parsed);
		}
		else
		{
			// just compare the length as sometime the WKB string could differ in their ordering of elements
			Assert.assertEquals(wkb.length(), parsed.length());
		}
	}

	/**
	 * Test without using a database.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void testParserLocal()
	{
		//@formatter:off
		//these EWKB presentations were generated using the SQL: UNION ALL SELECT geometry_in('POINT(10 10)')::text
		// on all geometries in ParserTest.testParserUsingDb()
		testLocal("010100000000000000000024400000000000002440");
		testLocal("0101000080000000000000244000000000000024400000000000000000");
		testLocal("0101000080000000000000244000000000000024400000000000003440");
		testLocal("01010000807DC39425AD49B254320D2EFF9D25AB3DF168E388B5F8F4BE");
		testLocal("0101000040000000000000244000000000000024400000000000003440");
		testLocal("01010000C00000000000002440000000000000244000000000000034400000000000003E40");
		testLocal("010400000002000000010100000000000000000026400000000000002840010100000000000000000034400000000000003440");
		testLocal("0104000080020000000101000080000000000000264000000000000028400000000000002A400101000080000000000000344000000000000034400000000000003440");
		testLocal("0104000040020000000101000040000000000000264000000000000028400000000000002A400101000040000000000000344000000000000034400000000000003440");
		testLocal("01040000C00200000001010000C0000000000000264000000000000028400000000000002A400000000000002C4001010000C00000000000003440000000000000344000000000000034400000000000003440");
		testLocal("010400000002000000010100000000000000000026400000000000002840010100000000000000000034400000000000003440");
		testLocal("0104000080020000000101000080000000000000264000000000000028400000000000002A400101000080000000000000344000000000000034400000000000003440");
		testLocal("0104000040020000000101000040000000000000264000000000000028400000000000002A400101000040000000000000344000000000000034400000000000003440");
		testLocal("01040000C00200000001010000C0000000000000264000000000000028400000000000002A400000000000002C4001010000C00000000000003440000000000000344000000000000034400000000000003440");
		testLocal("01020000000400000000000000000024400000000000002440000000000000344000000000000034400000000000004940000000000000494000000000000041400000000000004140");
		testLocal("010200008004000000000000000000244000000000000024400000000000003440000000000000344000000000000034400000000000003440000000000000494000000000000049400000000000004940000000000000414000000000000041400000000000004140");
		testLocal("010200004004000000000000000000244000000000000024400000000000003440000000000000344000000000000034400000000000003440000000000000494000000000000049400000000000004940000000000000414000000000000041400000000000004140");
		testLocal("01020000C0040000000000000000002440000000000000244000000000000034400000000000003440000000000000344000000000000034400000000000003440000000000000344000000000000049400000000000004940000000000000494000000000000049400000000000004140000000000000414000000000000041400000000000004940");
		testLocal("010300000002000000050000000000000000002440000000000000244000000000000034400000000000002440000000000000344000000000000034400000000000003440000000000000244000000000000024400000000000002440050000000000000000001440000000000000144000000000000014400000000000001840000000000000184000000000000018400000000000001840000000000000144000000000000014400000000000001440");
		testLocal("0103000080020000000500000000000000000024400000000000002440000000000000000000000000000034400000000000002440000000000000000000000000000034400000000000003440000000000000000000000000000034400000000000002440000000000000000000000000000024400000000000002440000000000000000005000000000000000000144000000000000014400000000000000000000000000000144000000000000018400000000000000000000000000000184000000000000018400000000000000000000000000000184000000000000014400000000000000000000000000000144000000000000014400000000000000000");
		testLocal("0103000040020000000500000000000000000024400000000000002440000000000000000000000000000034400000000000002440000000000000000000000000000034400000000000003440000000000000000000000000000034400000000000002440000000000000000000000000000024400000000000002440000000000000000005000000000000000000144000000000000014400000000000000000000000000000144000000000000018400000000000000000000000000000184000000000000018400000000000000000000000000000184000000000000014400000000000000000000000000000144000000000000014400000000000000000");
		testLocal("01030000C002000000050000000000000000002440000000000000244000000000000000000000000000001C400000000000003440000000000000244000000000000000000000000000001C400000000000003440000000000000344000000000000000000000000000001C400000000000003440000000000000244000000000000000000000000000001C400000000000002440000000000000244000000000000000000000000000001C40050000000000000000001440000000000000144000000000000000000000000000001C400000000000001440000000000000184000000000000000000000000000001C400000000000001840000000000000184000000000000000000000000000001C400000000000001840000000000000144000000000000000000000000000001C400000000000001440000000000000144000000000000000000000000000001C40");
		testLocal("010600000002000000010300000002000000050000000000000000002440000000000000244000000000000034400000000000002440000000000000344000000000000034400000000000003440000000000000244000000000000024400000000000002440050000000000000000001440000000000000144000000000000014400000000000001840000000000000184000000000000018400000000000001840000000000000144000000000000014400000000000001440010300000002000000050000000000000000002440000000000000244000000000000034400000000000002440000000000000344000000000000034400000000000003440000000000000244000000000000024400000000000002440050000000000000000001440000000000000144000000000000014400000000000001840000000000000184000000000000018400000000000001840000000000000144000000000000014400000000000001440");
		testLocal("01060000800200000001030000800200000005000000000000000000244000000000000024400000000000000000000000000000344000000000000024400000000000000000000000000000344000000000000034400000000000000000000000000000344000000000000024400000000000000000000000000000244000000000000024400000000000000000050000000000000000001440000000000000144000000000000000000000000000001440000000000000184000000000000000000000000000001840000000000000184000000000000000000000000000001840000000000000144000000000000000000000000000001440000000000000144000000000000000000103000080020000000500000000000000000024400000000000002440000000000000000000000000000034400000000000002440000000000000000000000000000034400000000000003440000000000000000000000000000034400000000000002440000000000000000000000000000024400000000000002440000000000000000005000000000000000000144000000000000014400000000000000000000000000000144000000000000018400000000000000000000000000000184000000000000018400000000000000000000000000000184000000000000014400000000000000000000000000000144000000000000014400000000000000000");
		testLocal("01060000400200000001030000400200000005000000000000000000244000000000000024400000000000000000000000000000344000000000000024400000000000000000000000000000344000000000000034400000000000000000000000000000344000000000000024400000000000000000000000000000244000000000000024400000000000000000050000000000000000001440000000000000144000000000000000000000000000001440000000000000184000000000000000000000000000001840000000000000184000000000000000000000000000001840000000000000144000000000000000000000000000001440000000000000144000000000000000000103000040020000000500000000000000000024400000000000002440000000000000000000000000000034400000000000002440000000000000000000000000000034400000000000003440000000000000000000000000000034400000000000002440000000000000000000000000000024400000000000002440000000000000000005000000000000000000144000000000000014400000000000000000000000000000144000000000000018400000000000000000000000000000184000000000000018400000000000000000000000000000184000000000000014400000000000000000000000000000144000000000000014400000000000000000");
		testLocal("01060000C00200000001030000C002000000050000000000000000002440000000000000244000000000000000000000000000001C400000000000003440000000000000244000000000000000000000000000001C400000000000003440000000000000344000000000000000000000000000001C400000000000003440000000000000244000000000000000000000000000001C400000000000002440000000000000244000000000000000000000000000001C40050000000000000000001440000000000000144000000000000000000000000000001C400000000000001440000000000000184000000000000000000000000000001C400000000000001840000000000000184000000000000000000000000000001C400000000000001840000000000000144000000000000000000000000000001C400000000000001440000000000000144000000000000000000000000000001C4001030000C002000000050000000000000000002440000000000000244000000000000000000000000000001C400000000000003440000000000000244000000000000000000000000000001C400000000000003440000000000000344000000000000000000000000000001C400000000000003440000000000000244000000000000000000000000000001C400000000000002440000000000000244000000000000000000000000000001C40050000000000000000001440000000000000144000000000000000000000000000001C400000000000001440000000000000184000000000000000000000000000001C400000000000001840000000000000184000000000000000000000000000001C400000000000001840000000000000144000000000000000000000000000001C400000000000001440000000000000144000000000000000000000000000001C40");
		testLocal("01050000000200000001020000000500000000000000000024400000000000002440000000000000344000000000000024400000000000003440000000000000344000000000000034400000000000002440000000000000244000000000000024400102000000050000000000000000001440000000000000144000000000000014400000000000001840000000000000184000000000000018400000000000001840000000000000144000000000000014400000000000001440");
		testLocal("010500008002000000010200008005000000000000000000244000000000000024400000000000001440000000000000344000000000000024400000000000001440000000000000344000000000000034400000000000000000000000000000344000000000000024400000000000000000000000000000244000000000000024400000000000000000010200008005000000000000000000144000000000000014400000000000000000000000000000144000000000000018400000000000000000000000000000184000000000000018400000000000000000000000000000184000000000000014400000000000000000000000000000144000000000000014400000000000000000");
		testLocal("010500004002000000010200004005000000000000000000244000000000000024400000000000001C40000000000000344000000000000024400000000000001C40000000000000344000000000000034400000000000000000000000000000344000000000000024400000000000000000000000000000244000000000000024400000000000000000010200004005000000000000000000144000000000000014400000000000000000000000000000144000000000000018400000000000000000000000000000184000000000000018400000000000000000000000000000184000000000000014400000000000000000000000000000144000000000000014400000000000000000");
		testLocal("01050000C00200000001020000C0050000000000000000002440000000000000244000000000000000000000000000001C400000000000003440000000000000244000000000000000000000000000001C400000000000003440000000000000344000000000000000000000000000001C400000000000003440000000000000244000000000000000000000000000001C400000000000002440000000000000244000000000000000000000000000001C4001020000C0050000000000000000001440000000000000144000000000000000000000000000001C400000000000001440000000000000184000000000000000000000000000001C400000000000001840000000000000184000000000000000000000000000001C400000000000001840000000000000144000000000000000000000000000001C400000000000001440000000000000144000000000000000000000000000001C40");
		testLocal("010700000002000000010100000000000000000024400000000000002440010100000000000000000034400000000000003440");
		testLocal("01070000800200000001010000800000000000002440000000000000244000000000000034400101000080000000000000344000000000000034400000000000003440");
		testLocal("01070000C00200000001010000C00000000000002440000000000000244000000000000034400000000000001C4001010000C00000000000003440000000000000344000000000000034400000000000001C40");
		testLocal("010700008002000000010200008004000000000000000000244000000000000024400000000000003440000000000000344000000000000034400000000000003440000000000000494000000000000049400000000000004940000000000000414000000000000041400000000000004140010200008004000000000000000000244000000000000024400000000000003440000000000000344000000000000034400000000000003440000000000000494000000000000049400000000000004940000000000000414000000000000041400000000000004140");
		testLocal("01070000800200000001030000800200000005000000000000000000244000000000000024400000000000000000000000000000344000000000000024400000000000000000000000000000344000000000000034400000000000000000000000000000344000000000000024400000000000000000000000000000244000000000000024400000000000000000050000000000000000001440000000000000144000000000000000000000000000001440000000000000184000000000000000000000000000001840000000000000184000000000000000000000000000001840000000000000144000000000000000000000000000001440000000000000144000000000000000000103000080020000000500000000000000000024400000000000002440000000000000000000000000000034400000000000002440000000000000000000000000000034400000000000003440000000000000000000000000000034400000000000002440000000000000000000000000000024400000000000002440000000000000000005000000000000000000144000000000000014400000000000000000000000000000144000000000000018400000000000000000000000000000184000000000000018400000000000000000000000000000184000000000000014400000000000000000000000000000144000000000000014400000000000000000");
		testLocal("0107000080020000000104000080020000000101000080000000000000244000000000000024400000000000002440010100008000000000000034400000000000003440000000000000344001040000800200000001010000800000000000002440000000000000244000000000000024400101000080000000000000344000000000000034400000000000003440");
		testLocal("0107000080020000000104000080020000000101000080000000000000244000000000000024400000000000002440010100008000000000000034400000000000003440000000000000344001040000800200000001010000800000000000002440000000000000244000000000000024400101000080000000000000344000000000000034400000000000003440");
		testLocal("010700008001000000010500008002000000010200008005000000000000000000244000000000000024400000000000000000000000000000344000000000000024400000000000000000000000000000344000000000000034400000000000000000000000000000344000000000000024400000000000000000000000000000244000000000000024400000000000000000010200008005000000000000000000144000000000000014400000000000000000000000000000144000000000000018400000000000000000000000000000184000000000000018400000000000000000000000000000184000000000000014400000000000000000000000000000144000000000000014400000000000000000");
		testLocal("0107000080020000000106000080020000000103000080020000000500000000000000000024400000000000002440000000000000000000000000000034400000000000002440000000000000000000000000000034400000000000003440000000000000000000000000000034400000000000002440000000000000000000000000000024400000000000002440000000000000000005000000000000000000144000000000000014400000000000000000000000000000144000000000000018400000000000000000000000000000184000000000000018400000000000000000000000000000184000000000000014400000000000000000000000000000144000000000000014400000000000000000010300008002000000050000000000000000002440000000000000244000000000000000000000000000003440000000000000244000000000000000000000000000003440000000000000344000000000000000000000000000003440000000000000244000000000000000000000000000002440000000000000244000000000000000000500000000000000000014400000000000001440000000000000000000000000000014400000000000001840000000000000000000000000000018400000000000001840000000000000000000000000000018400000000000001440000000000000000000000000000014400000000000001440000000000000000001060000800200000001030000800200000005000000000000000000244000000000000024400000000000000000000000000000344000000000000024400000000000000000000000000000344000000000000034400000000000000000000000000000344000000000000024400000000000000000000000000000244000000000000024400000000000000000050000000000000000001440000000000000144000000000000000000000000000001440000000000000184000000000000000000000000000001840000000000000184000000000000000000000000000001840000000000000144000000000000000000000000000001440000000000000144000000000000000000103000080020000000500000000000000000024400000000000002440000000000000000000000000000034400000000000002440000000000000000000000000000034400000000000003440000000000000000000000000000034400000000000002440000000000000000000000000000024400000000000002440000000000000000005000000000000000000144000000000000014400000000000000000000000000000144000000000000018400000000000000000000000000000184000000000000018400000000000000000000000000000184000000000000014400000000000000000000000000000144000000000000014400000000000000000");
		testLocal("01070000800300000001010000800000000000002440000000000000244000000000000034400102000080040000000000000000002440000000000000244000000000000034400000000000003440000000000000344000000000000034400000000000004940000000000000494000000000000049400000000000004140000000000000414000000000000041400103000080020000000500000000000000000024400000000000002440000000000000000000000000000034400000000000002440000000000000000000000000000034400000000000003440000000000000000000000000000034400000000000002440000000000000000000000000000024400000000000002440000000000000000005000000000000000000144000000000000014400000000000000000000000000000144000000000000018400000000000000000000000000000184000000000000018400000000000000000000000000000184000000000000014400000000000000000000000000000144000000000000014400000000000000000");
		testLocal("010700008006000000010100008000000000000024400000000000002440000000000000344001040000800200000001010000800000000000002440000000000000244000000000000024400101000080000000000000344000000000000034400000000000003440010200008004000000000000000000244000000000000024400000000000003440000000000000344000000000000034400000000000003440000000000000494000000000000049400000000000004940000000000000414000000000000041400000000000004140010300008002000000050000000000000000002440000000000000244000000000000000000000000000003440000000000000244000000000000000000000000000003440000000000000344000000000000000000000000000003440000000000000244000000000000000000000000000002440000000000000244000000000000000000500000000000000000014400000000000001440000000000000000000000000000014400000000000001840000000000000000000000000000018400000000000001840000000000000000000000000000018400000000000001440000000000000000000000000000014400000000000001440000000000000000001060000800200000001030000800200000005000000000000000000244000000000000024400000000000000000000000000000344000000000000024400000000000000000000000000000344000000000000034400000000000000000000000000000344000000000000024400000000000000000000000000000244000000000000024400000000000000000050000000000000000001440000000000000144000000000000000000000000000001440000000000000184000000000000000000000000000001840000000000000184000000000000000000000000000001840000000000000144000000000000000000000000000001440000000000000144000000000000000000103000080020000000500000000000000000024400000000000002440000000000000000000000000000034400000000000002440000000000000000000000000000034400000000000003440000000000000000000000000000034400000000000002440000000000000000000000000000024400000000000002440000000000000000005000000000000000000144000000000000014400000000000000000000000000000144000000000000018400000000000000000000000000000184000000000000018400000000000000000000000000000184000000000000014400000000000000000000000000000144000000000000014400000000000000000010500008002000000010200008005000000000000000000244000000000000024400000000000000000000000000000344000000000000024400000000000000000000000000000344000000000000034400000000000000000000000000000344000000000000024400000000000000000000000000000244000000000000024400000000000000000010200008005000000000000000000144000000000000014400000000000000000000000000000144000000000000018400000000000000000000000000000184000000000000018400000000000000000000000000000184000000000000014400000000000000000000000000000144000000000000014400000000000000000");
		testLocal("010700008006000000010100008000000000000024400000000000002440000000000000344001040000800200000001010000800000000000002440000000000000244000000000000024400101000080000000000000344000000000000034400000000000003440010200008004000000000000000000244000000000000024400000000000003440000000000000344000000000000034400000000000003440000000000000494000000000000049400000000000004940000000000000414000000000000041400000000000004140010300008002000000050000000000000000002440000000000000244000000000000000000000000000003440000000000000244000000000000000000000000000003440000000000000344000000000000000000000000000003440000000000000244000000000000000000000000000002440000000000000244000000000000000000500000000000000000014400000000000001440000000000000000000000000000014400000000000001840000000000000000000000000000018400000000000001840000000000000000000000000000018400000000000001440000000000000000000000000000014400000000000001440000000000000000001060000800200000001030000800200000005000000000000000000244000000000000024400000000000000000000000000000344000000000000024400000000000000000000000000000344000000000000034400000000000000000000000000000344000000000000024400000000000000000000000000000244000000000000024400000000000000000050000000000000000001440000000000000144000000000000000000000000000001440000000000000184000000000000000000000000000001840000000000000184000000000000000000000000000001840000000000000144000000000000000000000000000001440000000000000144000000000000000000103000080020000000500000000000000000024400000000000002440000000000000000000000000000034400000000000002440000000000000000000000000000034400000000000003440000000000000000000000000000034400000000000002440000000000000000000000000000024400000000000002440000000000000000005000000000000000000144000000000000014400000000000000000000000000000144000000000000018400000000000000000000000000000184000000000000018400000000000000000000000000000184000000000000014400000000000000000000000000000144000000000000014400000000000000000010500008002000000010200008005000000000000000000244000000000000024400000000000000000000000000000344000000000000024400000000000000000000000000000344000000000000034400000000000000000000000000000344000000000000024400000000000000000000000000000244000000000000024400000000000000000010200008005000000000000000000144000000000000014400000000000000000000000000000144000000000000018400000000000000000000000000000184000000000000018400000000000000000000000000000184000000000000014400000000000000000000000000000144000000000000014400000000000000000");
		testLocal("010700000000000000");
		testLocal("01070000400200000001010000400000000000002440000000000000244000000000000034400101000040000000000000344000000000000034400000000000003440");
		testLocal("01080000000300000000000000000022C0000000000000004000000000000020C000000000000008400000000000001CC00000000000000040");
		testLocal("0108000000050000000000000000000000000000000000F0BF000000000000F0BF00000000000000000000000000000000000000000000F03F000000000000F03F00000000000000000000000000000000000000000000F0BF");
		testLocal("010A000000020000000102000000050000000000000000000000000000000000000000000000000010400000000000000000000000000000104000000000000010400000000000000000000000000000104000000000000000000000000000000000010200000004000000000000000000F03F000000000000F03F000000000000084000000000000008400000000000000840000000000000F03F000000000000F03F000000000000F03F");
		testLocal("010A000000020000000108000000050000000000000000000000000000000000000000000000000010400000000000000000000000000000104000000000000010400000000000000000000000000000104000000000000000000000000000000000010200000004000000000000000000F03F000000000000F03F000000000000084000000000000008400000000000000840000000000000F03F000000000000F03F000000000000F03F");
		testLocal("01090000000200000001080000000300000000000000000000000000000000000000000000000000F03F000000000000F03F000000000000F03F0000000000000000010200000002000000000000000000F03F00000000000000000000000000000000000000000000F03F");
		testLocal("010B000000020000000102000000020000000000000000000000000000000000000000000000000014400000000000001440010800000003000000000000000000104000000000000000000000000000001040000000000000104000000000000020400000000000001040");
		// for the MultiSurface do not compare the full WKB string as the order seems to differ, just compare the length
		testLocal("010C00000002000000010A000000020000000108000000050000000000000000000000000000000000000000000000000010400000000000000000000000000000104000000000000010400000000000000000000000000000104000000000000000000000000000000000010200000004000000000000000000F03F000000000000F03F000000000000084000000000000008400000000000000840000000000000F03F000000000000F03F000000000000F03F01030000000200000004000000000000000000244000000000000024400000000000002C40000000000000284000000000000026400000000000002440000000000000244000000000000024400400000000000000000026400000000000002640000000000000274000000000000026400000000000002640000000000000274000000000000026400000000000002640"
				,false);
		//@formatter:on
	}

}