package alternate;

import standard.AESStages;
import utilities.Functions;

/**
 * Class providing the basic stages for AES encryption and decryption using table lookup.
 *
 */
public class AESStages2 extends AESStages {

	/**
	 * Table 0 for optimized AES, used in encryption
	 */
	public static final String[] T0 = { "00000000", "02010103", "04020206",
			"06030305", "0804040C", "0A05050F", "0C06060A", "0E070709",
			"10080818", "1209091B", "140A0A1E", "160B0B1D", "180C0C14",
			"1A0D0D17", "1C0E0E12", "1E0F0F11", "20101030", "22111133",
			"24121236", "26131335", "2814143C", "2A15153F", "2C16163A",
			"2E171739", "30181828", "3219192B", "341A1A2E", "361B1B2D",
			"381C1C24", "3A1D1D27", "3C1E1E22", "3E1F1F21", "40202060",
			"42212163", "44222266", "46232365", "4824246C", "4A25256F",
			"4C26266A", "4E272769", "50282878", "5229297B", "542A2A7E",
			"562B2B7D", "582C2C74", "5A2D2D77", "5C2E2E72", "5E2F2F71",
			"60303050", "62313153", "64323256", "66333355", "6834345C",
			"6A35355F", "6C36365A", "6E373759", "70383848", "7239394B",
			"743A3A4E", "763B3B4D", "783C3C44", "7A3D3D47", "7C3E3E42",
			"7E3F3F41", "804040C0", "824141C3", "844242C6", "864343C5",
			"884444CC", "8A4545CF", "8C4646CA", "8E4747C9", "904848D8",
			"924949DB", "944A4ADE", "964B4BDD", "984C4CD4", "9A4D4DD7",
			"9C4E4ED2", "9E4F4FD1", "A05050F0", "A25151F3", "A45252F6",
			"A65353F5", "A85454FC", "AA5555FF", "AC5656FA", "AE5757F9",
			"B05858E8", "B25959EB", "B45A5AEE", "B65B5BED", "B85C5CE4",
			"BA5D5DE7", "BC5E5EE2", "BE5F5FE1", "C06060A0", "C26161A3",
			"C46262A6", "C66363A5", "C86464AC", "CA6565AF", "CC6666AA",
			"CE6767A9", "D06868B8", "D26969BB", "D46A6ABE", "D66B6BBD",
			"D86C6CB4", "DA6D6DB7", "DC6E6EB2", "DE6F6FB1", "E0707090",
			"E2717193", "E4727296", "E6737395", "E874749C", "EA75759F",
			"EC76769A", "EE777799", "F0787888", "F279798B", "F47A7A8E",
			"F67B7B8D", "F87C7C84", "FA7D7D87", "FC7E7E82", "FE7F7F81",
			"1B80809B", "19818198", "1F82829D", "1D83839E", "13848497",
			"11858594", "17868691", "15878792", "0B888883", "09898980",
			"0F8A8A85", "0D8B8B86", "038C8C8F", "018D8D8C", "078E8E89",
			"058F8F8A", "3B9090AB", "399191A8", "3F9292AD", "3D9393AE",
			"339494A7", "319595A4", "379696A1", "359797A2", "2B9898B3",
			"299999B0", "2F9A9AB5", "2D9B9BB6", "239C9CBF", "219D9DBC",
			"279E9EB9", "259F9FBA", "5BA0A0FB", "59A1A1F8", "5FA2A2FD",
			"5DA3A3FE", "53A4A4F7", "51A5A5F4", "57A6A6F1", "55A7A7F2",
			"4BA8A8E3", "49A9A9E0", "4FAAAAE5", "4DABABE6", "43ACACEF",
			"41ADADEC", "47AEAEE9", "45AFAFEA", "7BB0B0CB", "79B1B1C8",
			"7FB2B2CD", "7DB3B3CE", "73B4B4C7", "71B5B5C4", "77B6B6C1",
			"75B7B7C2", "6BB8B8D3", "69B9B9D0", "6FBABAD5", "6DBBBBD6",
			"63BCBCDF", "61BDBDDC", "67BEBED9", "65BFBFDA", "9BC0C05B",
			"99C1C158", "9FC2C25D", "9DC3C35E", "93C4C457", "91C5C554",
			"97C6C651", "95C7C752", "8BC8C843", "89C9C940", "8FCACA45",
			"8DCBCB46", "83CCCC4F", "81CDCD4C", "87CECE49", "85CFCF4A",
			"BBD0D06B", "B9D1D168", "BFD2D26D", "BDD3D36E", "B3D4D467",
			"B1D5D564", "B7D6D661", "B5D7D762", "ABD8D873", "A9D9D970",
			"AFDADA75", "ADDBDB76", "A3DCDC7F", "A1DDDD7C", "A7DEDE79",
			"A5DFDF7A", "DBE0E03B", "D9E1E138", "DFE2E23D", "DDE3E33E",
			"D3E4E437", "D1E5E534", "D7E6E631", "D5E7E732", "CBE8E823",
			"C9E9E920", "CFEAEA25", "CDEBEB26", "C3ECEC2F", "C1EDED2C",
			"C7EEEE29", "C5EFEF2A", "FBF0F00B", "F9F1F108", "FFF2F20D",
			"FDF3F30E", "F3F4F407", "F1F5F504", "F7F6F601", "F5F7F702",
			"EBF8F813", "E9F9F910", "EFFAFA15", "EDFBFB16", "E3FCFC1F",
			"E1FDFD1C", "E7FEFE19", "E5FFFF1A" };

	/**
	 * Table 1 for optimized AES, used in encryption
	 */
	public static final String[] T1 = { "00000000", "03020101", "06040202",
			"05060303", "0C080404", "0F0A0505", "0A0C0606", "090E0707",
			"18100808", "1B120909", "1E140A0A", "1D160B0B", "14180C0C",
			"171A0D0D", "121C0E0E", "111E0F0F", "30201010", "33221111",
			"36241212", "35261313", "3C281414", "3F2A1515", "3A2C1616",
			"392E1717", "28301818", "2B321919", "2E341A1A", "2D361B1B",
			"24381C1C", "273A1D1D", "223C1E1E", "213E1F1F", "60402020",
			"63422121", "66442222", "65462323", "6C482424", "6F4A2525",
			"6A4C2626", "694E2727", "78502828", "7B522929", "7E542A2A",
			"7D562B2B", "74582C2C", "775A2D2D", "725C2E2E", "715E2F2F",
			"50603030", "53623131", "56643232", "55663333", "5C683434",
			"5F6A3535", "5A6C3636", "596E3737", "48703838", "4B723939",
			"4E743A3A", "4D763B3B", "44783C3C", "477A3D3D", "427C3E3E",
			"417E3F3F", "C0804040", "C3824141", "C6844242", "C5864343",
			"CC884444", "CF8A4545", "CA8C4646", "C98E4747", "D8904848",
			"DB924949", "DE944A4A", "DD964B4B", "D4984C4C", "D79A4D4D",
			"D29C4E4E", "D19E4F4F", "F0A05050", "F3A25151", "F6A45252",
			"F5A65353", "FCA85454", "FFAA5555", "FAAC5656", "F9AE5757",
			"E8B05858", "EBB25959", "EEB45A5A", "EDB65B5B", "E4B85C5C",
			"E7BA5D5D", "E2BC5E5E", "E1BE5F5F", "A0C06060", "A3C26161",
			"A6C46262", "A5C66363", "ACC86464", "AFCA6565", "AACC6666",
			"A9CE6767", "B8D06868", "BBD26969", "BED46A6A", "BDD66B6B",
			"B4D86C6C", "B7DA6D6D", "B2DC6E6E", "B1DE6F6F", "90E07070",
			"93E27171", "96E47272", "95E67373", "9CE87474", "9FEA7575",
			"9AEC7676", "99EE7777", "88F07878", "8BF27979", "8EF47A7A",
			"8DF67B7B", "84F87C7C", "87FA7D7D", "82FC7E7E", "81FE7F7F",
			"9B1B8080", "98198181", "9D1F8282", "9E1D8383", "97138484",
			"94118585", "91178686", "92158787", "830B8888", "80098989",
			"850F8A8A", "860D8B8B", "8F038C8C", "8C018D8D", "89078E8E",
			"8A058F8F", "AB3B9090", "A8399191", "AD3F9292", "AE3D9393",
			"A7339494", "A4319595", "A1379696", "A2359797", "B32B9898",
			"B0299999", "B52F9A9A", "B62D9B9B", "BF239C9C", "BC219D9D",
			"B9279E9E", "BA259F9F", "FB5BA0A0", "F859A1A1", "FD5FA2A2",
			"FE5DA3A3", "F753A4A4", "F451A5A5", "F157A6A6", "F255A7A7",
			"E34BA8A8", "E049A9A9", "E54FAAAA", "E64DABAB", "EF43ACAC",
			"EC41ADAD", "E947AEAE", "EA45AFAF", "CB7BB0B0", "C879B1B1",
			"CD7FB2B2", "CE7DB3B3", "C773B4B4", "C471B5B5", "C177B6B6",
			"C275B7B7", "D36BB8B8", "D069B9B9", "D56FBABA", "D66DBBBB",
			"DF63BCBC", "DC61BDBD", "D967BEBE", "DA65BFBF", "5B9BC0C0",
			"5899C1C1", "5D9FC2C2", "5E9DC3C3", "5793C4C4", "5491C5C5",
			"5197C6C6", "5295C7C7", "438BC8C8", "4089C9C9", "458FCACA",
			"468DCBCB", "4F83CCCC", "4C81CDCD", "4987CECE", "4A85CFCF",
			"6BBBD0D0", "68B9D1D1", "6DBFD2D2", "6EBDD3D3", "67B3D4D4",
			"64B1D5D5", "61B7D6D6", "62B5D7D7", "73ABD8D8", "70A9D9D9",
			"75AFDADA", "76ADDBDB", "7FA3DCDC", "7CA1DDDD", "79A7DEDE",
			"7AA5DFDF", "3BDBE0E0", "38D9E1E1", "3DDFE2E2", "3EDDE3E3",
			"37D3E4E4", "34D1E5E5", "31D7E6E6", "32D5E7E7", "23CBE8E8",
			"20C9E9E9", "25CFEAEA", "26CDEBEB", "2FC3ECEC", "2CC1EDED",
			"29C7EEEE", "2AC5EFEF", "0BFBF0F0", "08F9F1F1", "0DFFF2F2",
			"0EFDF3F3", "07F3F4F4", "04F1F5F5", "01F7F6F6", "02F5F7F7",
			"13EBF8F8", "10E9F9F9", "15EFFAFA", "16EDFBFB", "1FE3FCFC",
			"1CE1FDFD", "19E7FEFE", "1AE5FFFF" };

	/**
	 * Table 2 for optimized AES, used in encryption
	 */
	public static final String[] T2 = { "00000000", "01030201", "02060402",
			"03050603", "040C0804", "050F0A05", "060A0C06", "07090E07",
			"08181008", "091B1209", "0A1E140A", "0B1D160B", "0C14180C",
			"0D171A0D", "0E121C0E", "0F111E0F", "10302010", "11332211",
			"12362412", "13352613", "143C2814", "153F2A15", "163A2C16",
			"17392E17", "18283018", "192B3219", "1A2E341A", "1B2D361B",
			"1C24381C", "1D273A1D", "1E223C1E", "1F213E1F", "20604020",
			"21634221", "22664422", "23654623", "246C4824", "256F4A25",
			"266A4C26", "27694E27", "28785028", "297B5229", "2A7E542A",
			"2B7D562B", "2C74582C", "2D775A2D", "2E725C2E", "2F715E2F",
			"30506030", "31536231", "32566432", "33556633", "345C6834",
			"355F6A35", "365A6C36", "37596E37", "38487038", "394B7239",
			"3A4E743A", "3B4D763B", "3C44783C", "3D477A3D", "3E427C3E",
			"3F417E3F", "40C08040", "41C38241", "42C68442", "43C58643",
			"44CC8844", "45CF8A45", "46CA8C46", "47C98E47", "48D89048",
			"49DB9249", "4ADE944A", "4BDD964B", "4CD4984C", "4DD79A4D",
			"4ED29C4E", "4FD19E4F", "50F0A050", "51F3A251", "52F6A452",
			"53F5A653", "54FCA854", "55FFAA55", "56FAAC56", "57F9AE57",
			"58E8B058", "59EBB259", "5AEEB45A", "5BEDB65B", "5CE4B85C",
			"5DE7BA5D", "5EE2BC5E", "5FE1BE5F", "60A0C060", "61A3C261",
			"62A6C462", "63A5C663", "64ACC864", "65AFCA65", "66AACC66",
			"67A9CE67", "68B8D068", "69BBD269", "6ABED46A", "6BBDD66B",
			"6CB4D86C", "6DB7DA6D", "6EB2DC6E", "6FB1DE6F", "7090E070",
			"7193E271", "7296E472", "7395E673", "749CE874", "759FEA75",
			"769AEC76", "7799EE77", "7888F078", "798BF279", "7A8EF47A",
			"7B8DF67B", "7C84F87C", "7D87FA7D", "7E82FC7E", "7F81FE7F",
			"809B1B80", "81981981", "829D1F82", "839E1D83", "84971384",
			"85941185", "86911786", "87921587", "88830B88", "89800989",
			"8A850F8A", "8B860D8B", "8C8F038C", "8D8C018D", "8E89078E",
			"8F8A058F", "90AB3B90", "91A83991", "92AD3F92", "93AE3D93",
			"94A73394", "95A43195", "96A13796", "97A23597", "98B32B98",
			"99B02999", "9AB52F9A", "9BB62D9B", "9CBF239C", "9DBC219D",
			"9EB9279E", "9FBA259F", "A0FB5BA0", "A1F859A1", "A2FD5FA2",
			"A3FE5DA3", "A4F753A4", "A5F451A5", "A6F157A6", "A7F255A7",
			"A8E34BA8", "A9E049A9", "AAE54FAA", "ABE64DAB", "ACEF43AC",
			"ADEC41AD", "AEE947AE", "AFEA45AF", "B0CB7BB0", "B1C879B1",
			"B2CD7FB2", "B3CE7DB3", "B4C773B4", "B5C471B5", "B6C177B6",
			"B7C275B7", "B8D36BB8", "B9D069B9", "BAD56FBA", "BBD66DBB",
			"BCDF63BC", "BDDC61BD", "BED967BE", "BFDA65BF", "C05B9BC0",
			"C15899C1", "C25D9FC2", "C35E9DC3", "C45793C4", "C55491C5",
			"C65197C6", "C75295C7", "C8438BC8", "C94089C9", "CA458FCA",
			"CB468DCB", "CC4F83CC", "CD4C81CD", "CE4987CE", "CF4A85CF",
			"D06BBBD0", "D168B9D1", "D26DBFD2", "D36EBDD3", "D467B3D4",
			"D564B1D5", "D661B7D6", "D762B5D7", "D873ABD8", "D970A9D9",
			"DA75AFDA", "DB76ADDB", "DC7FA3DC", "DD7CA1DD", "DE79A7DE",
			"DF7AA5DF", "E03BDBE0", "E138D9E1", "E23DDFE2", "E33EDDE3",
			"E437D3E4", "E534D1E5", "E631D7E6", "E732D5E7", "E823CBE8",
			"E920C9E9", "EA25CFEA", "EB26CDEB", "EC2FC3EC", "ED2CC1ED",
			"EE29C7EE", "EF2AC5EF", "F00BFBF0", "F108F9F1", "F20DFFF2",
			"F30EFDF3", "F407F3F4", "F504F1F5", "F601F7F6", "F702F5F7",
			"F813EBF8", "F910E9F9", "FA15EFFA", "FB16EDFB", "FC1FE3FC",
			"FD1CE1FD", "FE19E7FE", "FF1AE5FF" };

	/**
	 * Table 3 for optimized AES, used in encryption
	 */
	public static final String[] T3 = { "00000000", "01010302", "02020604",
			"03030506", "04040C08", "05050F0A", "06060A0C", "0707090E",
			"08081810", "09091B12", "0A0A1E14", "0B0B1D16", "0C0C1418",
			"0D0D171A", "0E0E121C", "0F0F111E", "10103020", "11113322",
			"12123624", "13133526", "14143C28", "15153F2A", "16163A2C",
			"1717392E", "18182830", "19192B32", "1A1A2E34", "1B1B2D36",
			"1C1C2438", "1D1D273A", "1E1E223C", "1F1F213E", "20206040",
			"21216342", "22226644", "23236546", "24246C48", "25256F4A",
			"26266A4C", "2727694E", "28287850", "29297B52", "2A2A7E54",
			"2B2B7D56", "2C2C7458", "2D2D775A", "2E2E725C", "2F2F715E",
			"30305060", "31315362", "32325664", "33335566", "34345C68",
			"35355F6A", "36365A6C", "3737596E", "38384870", "39394B72",
			"3A3A4E74", "3B3B4D76", "3C3C4478", "3D3D477A", "3E3E427C",
			"3F3F417E", "4040C080", "4141C382", "4242C684", "4343C586",
			"4444CC88", "4545CF8A", "4646CA8C", "4747C98E", "4848D890",
			"4949DB92", "4A4ADE94", "4B4BDD96", "4C4CD498", "4D4DD79A",
			"4E4ED29C", "4F4FD19E", "5050F0A0", "5151F3A2", "5252F6A4",
			"5353F5A6", "5454FCA8", "5555FFAA", "5656FAAC", "5757F9AE",
			"5858E8B0", "5959EBB2", "5A5AEEB4", "5B5BEDB6", "5C5CE4B8",
			"5D5DE7BA", "5E5EE2BC", "5F5FE1BE", "6060A0C0", "6161A3C2",
			"6262A6C4", "6363A5C6", "6464ACC8", "6565AFCA", "6666AACC",
			"6767A9CE", "6868B8D0", "6969BBD2", "6A6ABED4", "6B6BBDD6",
			"6C6CB4D8", "6D6DB7DA", "6E6EB2DC", "6F6FB1DE", "707090E0",
			"717193E2", "727296E4", "737395E6", "74749CE8", "75759FEA",
			"76769AEC", "777799EE", "787888F0", "79798BF2", "7A7A8EF4",
			"7B7B8DF6", "7C7C84F8", "7D7D87FA", "7E7E82FC", "7F7F81FE",
			"80809B1B", "81819819", "82829D1F", "83839E1D", "84849713",
			"85859411", "86869117", "87879215", "8888830B", "89898009",
			"8A8A850F", "8B8B860D", "8C8C8F03", "8D8D8C01", "8E8E8907",
			"8F8F8A05", "9090AB3B", "9191A839", "9292AD3F", "9393AE3D",
			"9494A733", "9595A431", "9696A137", "9797A235", "9898B32B",
			"9999B029", "9A9AB52F", "9B9BB62D", "9C9CBF23", "9D9DBC21",
			"9E9EB927", "9F9FBA25", "A0A0FB5B", "A1A1F859", "A2A2FD5F",
			"A3A3FE5D", "A4A4F753", "A5A5F451", "A6A6F157", "A7A7F255",
			"A8A8E34B", "A9A9E049", "AAAAE54F", "ABABE64D", "ACACEF43",
			"ADADEC41", "AEAEE947", "AFAFEA45", "B0B0CB7B", "B1B1C879",
			"B2B2CD7F", "B3B3CE7D", "B4B4C773", "B5B5C471", "B6B6C177",
			"B7B7C275", "B8B8D36B", "B9B9D069", "BABAD56F", "BBBBD66D",
			"BCBCDF63", "BDBDDC61", "BEBED967", "BFBFDA65", "C0C05B9B",
			"C1C15899", "C2C25D9F", "C3C35E9D", "C4C45793", "C5C55491",
			"C6C65197", "C7C75295", "C8C8438B", "C9C94089", "CACA458F",
			"CBCB468D", "CCCC4F83", "CDCD4C81", "CECE4987", "CFCF4A85",
			"D0D06BBB", "D1D168B9", "D2D26DBF", "D3D36EBD", "D4D467B3",
			"D5D564B1", "D6D661B7", "D7D762B5", "D8D873AB", "D9D970A9",
			"DADA75AF", "DBDB76AD", "DCDC7FA3", "DDDD7CA1", "DEDE79A7",
			"DFDF7AA5", "E0E03BDB", "E1E138D9", "E2E23DDF", "E3E33EDD",
			"E4E437D3", "E5E534D1", "E6E631D7", "E7E732D5", "E8E823CB",
			"E9E920C9", "EAEA25CF", "EBEB26CD", "ECEC2FC3", "EDED2CC1",
			"EEEE29C7", "EFEF2AC5", "F0F00BFB", "F1F108F9", "F2F20DFF",
			"F3F30EFD", "F4F407F3", "F5F504F1", "F6F601F7", "F7F702F5",
			"F8F813EB", "F9F910E9", "FAFA15EF", "FBFB16ED", "FCFC1FE3",
			"FDFD1CE1", "FEFE19E7", "FFFF1AE5" };

	/**
	 * Table 0 prime for optimized AES, used in decryption
	 */
	public static final String[] T0Prime = { "00000000", "0E090D0B",
			"1C121A16", "121B171D", "3824342C", "362D3927", "24362E3A",
			"2A3F2331", "70486858", "7E416553", "6C5A724E", "62537F45",
			"486C5C74", "4665517F", "547E4662", "5A774B69", "E090D0B0",
			"EE99DDBB", "FC82CAA6", "F28BC7AD", "D8B4E49C", "D6BDE997",
			"C4A6FE8A", "CAAFF381", "90D8B8E8", "9ED1B5E3", "8CCAA2FE",
			"82C3AFF5", "A8FC8CC4", "A6F581CF", "B4EE96D2", "BAE79BD9",
			"DB3BBB7B", "D532B670", "C729A16D", "C920AC66", "E31F8F57",
			"ED16825C", "FF0D9541", "F104984A", "AB73D323", "A57ADE28",
			"B761C935", "B968C43E", "9357E70F", "9D5EEA04", "8F45FD19",
			"814CF012", "3BAB6BCB", "35A266C0", "27B971DD", "29B07CD6",
			"038F5FE7", "0D8652EC", "1F9D45F1", "119448FA", "4BE30393",
			"45EA0E98", "57F11985", "59F8148E", "73C737BF", "7DCE3AB4",
			"6FD52DA9", "61DC20A2", "AD766DF6", "A37F60FD", "B16477E0",
			"BF6D7AEB", "955259DA", "9B5B54D1", "894043CC", "87494EC7",
			"DD3E05AE", "D33708A5", "C12C1FB8", "CF2512B3", "E51A3182",
			"EB133C89", "F9082B94", "F701269F", "4DE6BD46", "43EFB04D",
			"51F4A750", "5FFDAA5B", "75C2896A", "7BCB8461", "69D0937C",
			"67D99E77", "3DAED51E", "33A7D815", "21BCCF08", "2FB5C203",
			"058AE132", "0B83EC39", "1998FB24", "1791F62F", "764DD68D",
			"7844DB86", "6A5FCC9B", "6456C190", "4E69E2A1", "4060EFAA",
			"527BF8B7", "5C72F5BC", "0605BED5", "080CB3DE", "1A17A4C3",
			"141EA9C8", "3E218AF9", "302887F2", "223390EF", "2C3A9DE4",
			"96DD063D", "98D40B36", "8ACF1C2B", "84C61120", "AEF93211",
			"A0F03F1A", "B2EB2807", "BCE2250C", "E6956E65", "E89C636E",
			"FA877473", "F48E7978", "DEB15A49", "D0B85742", "C2A3405F",
			"CCAA4D54", "41ECDAF7", "4FE5D7FC", "5DFEC0E1", "53F7CDEA",
			"79C8EEDB", "77C1E3D0", "65DAF4CD", "6BD3F9C6", "31A4B2AF",
			"3FADBFA4", "2DB6A8B9", "23BFA5B2", "09808683", "07898B88",
			"15929C95", "1B9B919E", "A17C0A47", "AF75074C", "BD6E1051",
			"B3671D5A", "99583E6B", "97513360", "854A247D", "8B432976",
			"D134621F", "DF3D6F14", "CD267809", "C32F7502", "E9105633",
			"E7195B38", "F5024C25", "FB0B412E", "9AD7618C", "94DE6C87",
			"86C57B9A", "88CC7691", "A2F355A0", "ACFA58AB", "BEE14FB6",
			"B0E842BD", "EA9F09D4", "E49604DF", "F68D13C2", "F8841EC9",
			"D2BB3DF8", "DCB230F3", "CEA927EE", "C0A02AE5", "7A47B13C",
			"744EBC37", "6655AB2A", "685CA621", "42638510", "4C6A881B",
			"5E719F06", "5078920D", "0A0FD964", "0406D46F", "161DC372",
			"1814CE79", "322BED48", "3C22E043", "2E39F75E", "2030FA55",
			"EC9AB701", "E293BA0A", "F088AD17", "FE81A01C", "D4BE832D",
			"DAB78E26", "C8AC993B", "C6A59430", "9CD2DF59", "92DBD252",
			"80C0C54F", "8EC9C844", "A4F6EB75", "AAFFE67E", "B8E4F163",
			"B6EDFC68", "0C0A67B1", "02036ABA", "10187DA7", "1E1170AC",
			"342E539D", "3A275E96", "283C498B", "26354480", "7C420FE9",
			"724B02E2", "605015FF", "6E5918F4", "44663BC5", "4A6F36CE",
			"587421D3", "567D2CD8", "37A10C7A", "39A80171", "2BB3166C",
			"25BA1B67", "0F853856", "018C355D", "13972240", "1D9E2F4B",
			"47E96422", "49E06929", "5BFB7E34", "55F2733F", "7FCD500E",
			"71C45D05", "63DF4A18", "6DD64713", "D731DCCA", "D938D1C1",
			"CB23C6DC", "C52ACBD7", "EF15E8E6", "E11CE5ED", "F307F2F0",
			"FD0EFFFB", "A779B492", "A970B999", "BB6BAE84", "B562A38F",
			"9F5D80BE", "91548DB5", "834F9AA8", "8D4697A3" };

	/**
	 * Table 1 prime for optimized AES, used in decryption
	 */
	public static final String[] T1Prime = { "00000000", "0B0E090D",
			"161C121A", "1D121B17", "2C382434", "27362D39", "3A24362E",
			"312A3F23", "58704868", "537E4165", "4E6C5A72", "4562537F",
			"74486C5C", "7F466551", "62547E46", "695A774B", "B0E090D0",
			"BBEE99DD", "A6FC82CA", "ADF28BC7", "9CD8B4E4", "97D6BDE9",
			"8AC4A6FE", "81CAAFF3", "E890D8B8", "E39ED1B5", "FE8CCAA2",
			"F582C3AF", "C4A8FC8C", "CFA6F581", "D2B4EE96", "D9BAE79B",
			"7BDB3BBB", "70D532B6", "6DC729A1", "66C920AC", "57E31F8F",
			"5CED1682", "41FF0D95", "4AF10498", "23AB73D3", "28A57ADE",
			"35B761C9", "3EB968C4", "0F9357E7", "049D5EEA", "198F45FD",
			"12814CF0", "CB3BAB6B", "C035A266", "DD27B971", "D629B07C",
			"E7038F5F", "EC0D8652", "F11F9D45", "FA119448", "934BE303",
			"9845EA0E", "8557F119", "8E59F814", "BF73C737", "B47DCE3A",
			"A96FD52D", "A261DC20", "F6AD766D", "FDA37F60", "E0B16477",
			"EBBF6D7A", "DA955259", "D19B5B54", "CC894043", "C787494E",
			"AEDD3E05", "A5D33708", "B8C12C1F", "B3CF2512", "82E51A31",
			"89EB133C", "94F9082B", "9FF70126", "464DE6BD", "4D43EFB0",
			"5051F4A7", "5B5FFDAA", "6A75C289", "617BCB84", "7C69D093",
			"7767D99E", "1E3DAED5", "1533A7D8", "0821BCCF", "032FB5C2",
			"32058AE1", "390B83EC", "241998FB", "2F1791F6", "8D764DD6",
			"867844DB", "9B6A5FCC", "906456C1", "A14E69E2", "AA4060EF",
			"B7527BF8", "BC5C72F5", "D50605BE", "DE080CB3", "C31A17A4",
			"C8141EA9", "F93E218A", "F2302887", "EF223390", "E42C3A9D",
			"3D96DD06", "3698D40B", "2B8ACF1C", "2084C611", "11AEF932",
			"1AA0F03F", "07B2EB28", "0CBCE225", "65E6956E", "6EE89C63",
			"73FA8774", "78F48E79", "49DEB15A", "42D0B857", "5FC2A340",
			"54CCAA4D", "F741ECDA", "FC4FE5D7", "E15DFEC0", "EA53F7CD",
			"DB79C8EE", "D077C1E3", "CD65DAF4", "C66BD3F9", "AF31A4B2",
			"A43FADBF", "B92DB6A8", "B223BFA5", "83098086", "8807898B",
			"9515929C", "9E1B9B91", "47A17C0A", "4CAF7507", "51BD6E10",
			"5AB3671D", "6B99583E", "60975133", "7D854A24", "768B4329",
			"1FD13462", "14DF3D6F", "09CD2678", "02C32F75", "33E91056",
			"38E7195B", "25F5024C", "2EFB0B41", "8C9AD761", "8794DE6C",
			"9A86C57B", "9188CC76", "A0A2F355", "ABACFA58", "B6BEE14F",
			"BDB0E842", "D4EA9F09", "DFE49604", "C2F68D13", "C9F8841E",
			"F8D2BB3D", "F3DCB230", "EECEA927", "E5C0A02A", "3C7A47B1",
			"37744EBC", "2A6655AB", "21685CA6", "10426385", "1B4C6A88",
			"065E719F", "0D507892", "640A0FD9", "6F0406D4", "72161DC3",
			"791814CE", "48322BED", "433C22E0", "5E2E39F7", "552030FA",
			"01EC9AB7", "0AE293BA", "17F088AD", "1CFE81A0", "2DD4BE83",
			"26DAB78E", "3BC8AC99", "30C6A594", "599CD2DF", "5292DBD2",
			"4F80C0C5", "448EC9C8", "75A4F6EB", "7EAAFFE6", "63B8E4F1",
			"68B6EDFC", "B10C0A67", "BA02036A", "A710187D", "AC1E1170",
			"9D342E53", "963A275E", "8B283C49", "80263544", "E97C420F",
			"E2724B02", "FF605015", "F46E5918", "C544663B", "CE4A6F36",
			"D3587421", "D8567D2C", "7A37A10C", "7139A801", "6C2BB316",
			"6725BA1B", "560F8538", "5D018C35", "40139722", "4B1D9E2F",
			"2247E964", "2949E069", "345BFB7E", "3F55F273", "0E7FCD50",
			"0571C45D", "1863DF4A", "136DD647", "CAD731DC", "C1D938D1",
			"DCCB23C6", "D7C52ACB", "E6EF15E8", "EDE11CE5", "F0F307F2",
			"FBFD0EFF", "92A779B4", "99A970B9", "84BB6BAE", "8FB562A3",
			"BE9F5D80", "B591548D", "A8834F9A", "A38D4697" };

	/**
	 * Table 2 prime for optimized AES, used in decryption
	 */
	public static final String[] T2Prime = { "00000000", "0D0B0E09",
			"1A161C12", "171D121B", "342C3824", "3927362D", "2E3A2436",
			"23312A3F", "68587048", "65537E41", "724E6C5A", "7F456253",
			"5C74486C", "517F4665", "4662547E", "4B695A77", "D0B0E090",
			"DDBBEE99", "CAA6FC82", "C7ADF28B", "E49CD8B4", "E997D6BD",
			"FE8AC4A6", "F381CAAF", "B8E890D8", "B5E39ED1", "A2FE8CCA",
			"AFF582C3", "8CC4A8FC", "81CFA6F5", "96D2B4EE", "9BD9BAE7",
			"BB7BDB3B", "B670D532", "A16DC729", "AC66C920", "8F57E31F",
			"825CED16", "9541FF0D", "984AF104", "D323AB73", "DE28A57A",
			"C935B761", "C43EB968", "E70F9357", "EA049D5E", "FD198F45",
			"F012814C", "6BCB3BAB", "66C035A2", "71DD27B9", "7CD629B0",
			"5FE7038F", "52EC0D86", "45F11F9D", "48FA1194", "03934BE3",
			"0E9845EA", "198557F1", "148E59F8", "37BF73C7", "3AB47DCE",
			"2DA96FD5", "20A261DC", "6DF6AD76", "60FDA37F", "77E0B164",
			"7AEBBF6D", "59DA9552", "54D19B5B", "43CC8940", "4EC78749",
			"05AEDD3E", "08A5D337", "1FB8C12C", "12B3CF25", "3182E51A",
			"3C89EB13", "2B94F908", "269FF701", "BD464DE6", "B04D43EF",
			"A75051F4", "AA5B5FFD", "896A75C2", "84617BCB", "937C69D0",
			"9E7767D9", "D51E3DAE", "D81533A7", "CF0821BC", "C2032FB5",
			"E132058A", "EC390B83", "FB241998", "F62F1791", "D68D764D",
			"DB867844", "CC9B6A5F", "C1906456", "E2A14E69", "EFAA4060",
			"F8B7527B", "F5BC5C72", "BED50605", "B3DE080C", "A4C31A17",
			"A9C8141E", "8AF93E21", "87F23028", "90EF2233", "9DE42C3A",
			"063D96DD", "0B3698D4", "1C2B8ACF", "112084C6", "3211AEF9",
			"3F1AA0F0", "2807B2EB", "250CBCE2", "6E65E695", "636EE89C",
			"7473FA87", "7978F48E", "5A49DEB1", "5742D0B8", "405FC2A3",
			"4D54CCAA", "DAF741EC", "D7FC4FE5", "C0E15DFE", "CDEA53F7",
			"EEDB79C8", "E3D077C1", "F4CD65DA", "F9C66BD3", "B2AF31A4",
			"BFA43FAD", "A8B92DB6", "A5B223BF", "86830980", "8B880789",
			"9C951592", "919E1B9B", "0A47A17C", "074CAF75", "1051BD6E",
			"1D5AB367", "3E6B9958", "33609751", "247D854A", "29768B43",
			"621FD134", "6F14DF3D", "7809CD26", "7502C32F", "5633E910",
			"5B38E719", "4C25F502", "412EFB0B", "618C9AD7", "6C8794DE",
			"7B9A86C5", "769188CC", "55A0A2F3", "58ABACFA", "4FB6BEE1",
			"42BDB0E8", "09D4EA9F", "04DFE496", "13C2F68D", "1EC9F884",
			"3DF8D2BB", "30F3DCB2", "27EECEA9", "2AE5C0A0", "B13C7A47",
			"BC37744E", "AB2A6655", "A621685C", "85104263", "881B4C6A",
			"9F065E71", "920D5078", "D9640A0F", "D46F0406", "C372161D",
			"CE791814", "ED48322B", "E0433C22", "F75E2E39", "FA552030",
			"B701EC9A", "BA0AE293", "AD17F088", "A01CFE81", "832DD4BE",
			"8E26DAB7", "993BC8AC", "9430C6A5", "DF599CD2", "D25292DB",
			"C54F80C0", "C8448EC9", "EB75A4F6", "E67EAAFF", "F163B8E4",
			"FC68B6ED", "67B10C0A", "6ABA0203", "7DA71018", "70AC1E11",
			"539D342E", "5E963A27", "498B283C", "44802635", "0FE97C42",
			"02E2724B", "15FF6050", "18F46E59", "3BC54466", "36CE4A6F",
			"21D35874", "2CD8567D", "0C7A37A1", "017139A8", "166C2BB3",
			"1B6725BA", "38560F85", "355D018C", "22401397", "2F4B1D9E",
			"642247E9", "692949E0", "7E345BFB", "733F55F2", "500E7FCD",
			"5D0571C4", "4A1863DF", "47136DD6", "DCCAD731", "D1C1D938",
			"C6DCCB23", "CBD7C52A", "E8E6EF15", "E5EDE11C", "F2F0F307",
			"FFFBFD0E", "B492A779", "B999A970", "AE84BB6B", "A38FB562",
			"80BE9F5D", "8DB59154", "9AA8834F", "97A38D46" };

	/**
	 * Table 3 prime for optimized AES, used in decryption
	 */
	public static final String[] T3Prime = { "00000000", "090D0B0E",
			"121A161C", "1B171D12", "24342C38", "2D392736", "362E3A24",
			"3F23312A", "48685870", "4165537E", "5A724E6C", "537F4562",
			"6C5C7448", "65517F46", "7E466254", "774B695A", "90D0B0E0",
			"99DDBBEE", "82CAA6FC", "8BC7ADF2", "B4E49CD8", "BDE997D6",
			"A6FE8AC4", "AFF381CA", "D8B8E890", "D1B5E39E", "CAA2FE8C",
			"C3AFF582", "FC8CC4A8", "F581CFA6", "EE96D2B4", "E79BD9BA",
			"3BBB7BDB", "32B670D5", "29A16DC7", "20AC66C9", "1F8F57E3",
			"16825CED", "0D9541FF", "04984AF1", "73D323AB", "7ADE28A5",
			"61C935B7", "68C43EB9", "57E70F93", "5EEA049D", "45FD198F",
			"4CF01281", "AB6BCB3B", "A266C035", "B971DD27", "B07CD629",
			"8F5FE703", "8652EC0D", "9D45F11F", "9448FA11", "E303934B",
			"EA0E9845", "F1198557", "F8148E59", "C737BF73", "CE3AB47D",
			"D52DA96F", "DC20A261", "766DF6AD", "7F60FDA3", "6477E0B1",
			"6D7AEBBF", "5259DA95", "5B54D19B", "4043CC89", "494EC787",
			"3E05AEDD", "3708A5D3", "2C1FB8C1", "2512B3CF", "1A3182E5",
			"133C89EB", "082B94F9", "01269FF7", "E6BD464D", "EFB04D43",
			"F4A75051", "FDAA5B5F", "C2896A75", "CB84617B", "D0937C69",
			"D99E7767", "AED51E3D", "A7D81533", "BCCF0821", "B5C2032F",
			"8AE13205", "83EC390B", "98FB2419", "91F62F17", "4DD68D76",
			"44DB8678", "5FCC9B6A", "56C19064", "69E2A14E", "60EFAA40",
			"7BF8B752", "72F5BC5C", "05BED506", "0CB3DE08", "17A4C31A",
			"1EA9C814", "218AF93E", "2887F230", "3390EF22", "3A9DE42C",
			"DD063D96", "D40B3698", "CF1C2B8A", "C6112084", "F93211AE",
			"F03F1AA0", "EB2807B2", "E2250CBC", "956E65E6", "9C636EE8",
			"877473FA", "8E7978F4", "B15A49DE", "B85742D0", "A3405FC2",
			"AA4D54CC", "ECDAF741", "E5D7FC4F", "FEC0E15D", "F7CDEA53",
			"C8EEDB79", "C1E3D077", "DAF4CD65", "D3F9C66B", "A4B2AF31",
			"ADBFA43F", "B6A8B92D", "BFA5B223", "80868309", "898B8807",
			"929C9515", "9B919E1B", "7C0A47A1", "75074CAF", "6E1051BD",
			"671D5AB3", "583E6B99", "51336097", "4A247D85", "4329768B",
			"34621FD1", "3D6F14DF", "267809CD", "2F7502C3", "105633E9",
			"195B38E7", "024C25F5", "0B412EFB", "D7618C9A", "DE6C8794",
			"C57B9A86", "CC769188", "F355A0A2", "FA58ABAC", "E14FB6BE",
			"E842BDB0", "9F09D4EA", "9604DFE4", "8D13C2F6", "841EC9F8",
			"BB3DF8D2", "B230F3DC", "A927EECE", "A02AE5C0", "47B13C7A",
			"4EBC3774", "55AB2A66", "5CA62168", "63851042", "6A881B4C",
			"719F065E", "78920D50", "0FD9640A", "06D46F04", "1DC37216",
			"14CE7918", "2BED4832", "22E0433C", "39F75E2E", "30FA5520",
			"9AB701EC", "93BA0AE2", "88AD17F0", "81A01CFE", "BE832DD4",
			"B78E26DA", "AC993BC8", "A59430C6", "D2DF599C", "DBD25292",
			"C0C54F80", "C9C8448E", "F6EB75A4", "FFE67EAA", "E4F163B8",
			"EDFC68B6", "0A67B10C", "036ABA02", "187DA710", "1170AC1E",
			"2E539D34", "275E963A", "3C498B28", "35448026", "420FE97C",
			"4B02E272", "5015FF60", "5918F46E", "663BC544", "6F36CE4A",
			"7421D358", "7D2CD856", "A10C7A37", "A8017139", "B3166C2B",
			"BA1B6725", "8538560F", "8C355D01", "97224013", "9E2F4B1D",
			"E9642247", "E0692949", "FB7E345B", "F2733F55", "CD500E7F",
			"C45D0571", "DF4A1863", "D647136D", "31DCCAD7", "38D1C1D9",
			"23C6DCCB", "2ACBD7C5", "15E8E6EF", "1CE5EDE1", "07F2F0F3",
			"0EFFFBFD", "79B492A7", "70B999A9", "6BAE84BB", "62A38FB5",
			"5D80BE9F", "548DB591", "4F9AA883", "4697A38D" };

	////////////////////////////////////////////////////////////////////////////////
	//
	// AES Encryption with Lookup Tables
	//
	////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * Xors the 4 arguments, each argument is a byte that is expanded to 4 bytes
	 * @param A - argument for T0
	 * @param B - argument for T1
	 * @param C - argument for T2
	 * @param D - argument for T3
	 * @param key - key for encryption round
	 * @return bitwise xor of all parameters
	 */
	public static String xorALL(String A, String B, String C, String D, String key) {
		String xor1 = Functions.xorWord(T0(A), T1(B));
		String xor2 = Functions.xorWord(T2(C), T3(D));
		String xor3 = Functions.xorWord(xor1, xor2);
		return Functions.xorWord(xor3, key);
	}
	
	/**
	 * Calculates T0 for the given byte, 2s + s + s + 3s
	 * @param s - Byte to be expanded
	 * @return expanded byte to 4 bytes from T0 array
	 */
	private static String T0(String s) {
		return T0[Integer.valueOf(subByte(s), 16)];
	}

	/**
	 * Calculates T1 for the given byte, 3s + 2s + s + s
	 * @param s - Byte to be expanded
	 * @return expanded byte to 4 bytes from T1 array
	 */
	private static String T1(String s) {
		return T1[Integer.valueOf(subByte(s), 16)];
	}

	/**
	 * Calculates T2 for the given byte, s + 3s + 2s + s
	 * @param s - Byte to be expanded
	 * @return expanded byte to 4 bytes from T2 array
	 */
	private static String T2(String s) {
		return T2[Integer.valueOf(subByte(s), 16)];
	}

	/**
	 * Calculates T3 for the given byte, s + s + 3s + 2s
	 * @param s - Byte to be expanded
	 * @return expanded byte to 4 bytes from T3 array
	 */
	private static String T3(String s) {
		return T3[Integer.valueOf(subByte(s), 16)];
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//
	// AES Decryption with Lookup Tables
	//
	////////////////////////////////////////////////////////////////////////////////

	/**
	 * Xors the 4 arguments, each argument is a byte that is expanded to 4 bytes
	 * @param A - argument for T0
	 * @param B - argument for T1
	 * @param C - argument for T2
	 * @param D - argument for T3
	 * @param key - key for decryption round
	 * @return bitwise xor of all parameters
	 */
	public static String xorALLPrime(String A, String B, String C, String D, String key) {
		String xor1 = Functions.xorWord(T0Prime(A), T1Prime(B));
		String xor2 = Functions.xorWord(T2Prime(C), T3Prime(D));
		String xor3 = Functions.xorWord(xor1, xor2);
		return Functions.xorWord(xor3, key);
	}

	/**
	 * Calculates T0Prime for the given byte, Es + 9s + Ds + Bs
	 * @param s - Byte to be expanded
	 * @return expanded byte to 4 bytes from T0Prime array
	 */
	private static String T0Prime(String s) {
		return T0Prime[Integer.valueOf(invSubByte(s), 16)];
	}

	/**
	 * Calculates T1Prime for the given byte, Bs + Es + 9s + Ds
	 * @param s - Byte to be expanded
	 * @return expanded byte to 4 bytes from T1Prime array
	 */
	private static String T1Prime(String s) {
		return T1Prime[Integer.valueOf(invSubByte(s), 16)];
	}

	/**
	 * Calculates T2Prime for the given byte, Ds + Bs + Es + 9s
	 * @param s - Byte to be expanded
	 * @return expanded byte to 4 bytes from T2Prime array
	 */
	private static String T2Prime(String s) {
		return T2Prime[Integer.valueOf(invSubByte(s), 16)];
	}

	/**
	 * Calculates T3Prime for the given byte, 9s + Ds + Bs + Es
	 * @param s - Byte to be expanded
	 * @return expanded byte to 4 bytes from T3Prime array
	 */
	private static String T3Prime(String s) {
		return T3Prime[Integer.valueOf(invSubByte(s), 16)];
	}

}
