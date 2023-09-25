/*Authors: Amisha Singhal(1722 3950) and Nikhil Kumar Singh(4050 0443)*/
package OneDollarRecognizer;

// import necessary libraries
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



// OneDollarRecognizer class for recognizer functions
public class OneDollarRecognizer extends Preprocess {

    //Necessary constants
    public Result bestResult;
    final static double SquareSize = 250.0;
    final static double Diagonal = Math.sqrt(SquareSize * SquareSize + SquareSize * SquareSize);
    final static double HalfDiagonal = 0.5 * Diagonal;
    final static double Phi = 0.5 * (-1.0 + Math.sqrt(5.0)); // Golden Ratio

    //public ArrayList<Template> Unistrokes = new ArrayList<>();

/*
    public void loadTemplates(){
        PointClass[] p1 = new PointClass[] { new PointClass(137,139),new PointClass(135,141),new PointClass(133,144),new PointClass(132,146),new PointClass(130,149),new PointClass(128,151),new PointClass(126,155),new PointClass(123,160),new PointClass(120,166),new PointClass(116,171),new PointClass(112,177),new PointClass(107,183),new PointClass(102,188),new PointClass(100,191),new PointClass(95,195),new PointClass(90,199),new PointClass(86,203),new PointClass(82,206),new PointClass(80,209),new PointClass(75,213),new PointClass(73,213),new PointClass(70,216),new PointClass(67,219),new PointClass(64,221),new PointClass(61,223),new PointClass(60,225),new PointClass(62,226),new PointClass(65,225),new PointClass(67,226),new PointClass(74,226),new PointClass(77,227),new PointClass(85,229),new PointClass(91,230),new PointClass(99,231),new PointClass(108,232),new PointClass(116,233),new PointClass(125,233),new PointClass(134,234),new PointClass(145,233),new PointClass(153,232),new PointClass(160,233),new PointClass(170,234),new PointClass(177,235),new PointClass(179,236),new PointClass(186,237),new PointClass(193,238),new PointClass(198,239),new PointClass(200,237),new PointClass(202,239),new PointClass(204,238),new PointClass(206,234),new PointClass(205,230),new PointClass(202,222),new PointClass(197,216),new PointClass(192,207),new PointClass(186,198),new PointClass(179,189),new PointClass(174,183),new PointClass(170,178),new PointClass(164,171),new PointClass(161,168),new PointClass(154,160),new PointClass(148,155),new PointClass(143,150),new PointClass(138,148),new PointClass(136,148) };
        ArrayList<PointClass> a1 = new ArrayList<>(Arrays.asList(p1));
        System.out.println(a1.size() + "name : triangle");
        Template tp1 = new Template("triangle", a1);

        Unistrokes.add(tp1);
        System.out.println(tp1.Points.size() + "name : triangle");

        PointClass[] p2 = new PointClass[] { new PointClass(87,142),new PointClass(89,145),new PointClass(91,148),new PointClass(93,151),new PointClass(96,155),new PointClass(98,157),new PointClass(100,160),new PointClass(102,162),new PointClass(106,167),new PointClass(108,169),new PointClass(110,171),new PointClass(115,177),new PointClass(119,183),new PointClass(123,189),new PointClass(127,193),new PointClass(129,196),new PointClass(133,200),new PointClass(137,206),new PointClass(140,209),new PointClass(143,212),new PointClass(146,215),new PointClass(151,220),new PointClass(153,222),new PointClass(155,223),new PointClass(157,225),new PointClass(158,223),new PointClass(157,218),new PointClass(155,211),new PointClass(154,208),new PointClass(152,200),new PointClass(150,189),new PointClass(148,179),new PointClass(147,170),new PointClass(147,158),new PointClass(147,148),new PointClass(147,141),new PointClass(147,136),new PointClass(144,135),new PointClass(142,137),new PointClass(140,139),new PointClass(135,145),new PointClass(131,152),new PointClass(124,163),new PointClass(116,177),new PointClass(108,191),new PointClass(100,206),new PointClass(94,217),new PointClass(91,222),new PointClass(89,225),new PointClass(87,226),new PointClass(87,224) };
        ArrayList<PointClass> a2 = new ArrayList<>(Arrays.asList(p2));
        System.out.println(a2.size() + "name : x");
        Template tp2 = new Template("x", a2);
        Unistrokes.add(tp2);

        PointClass[] p3 = new PointClass[] { new PointClass(78,149),new PointClass(78,153),new PointClass(78,157),new PointClass(78,160),new PointClass(79,162),new PointClass(79,164),new PointClass(79,167),new PointClass(79,169),new PointClass(79,173),new PointClass(79,178),new PointClass(79,183),new PointClass(80,189),new PointClass(80,193),new PointClass(80,198),new PointClass(80,202),new PointClass(81,208),new PointClass(81,210),new PointClass(81,216),new PointClass(82,222),new PointClass(82,224),new PointClass(82,227),new PointClass(83,229),new PointClass(83,231),new PointClass(85,230),new PointClass(88,232),new PointClass(90,233),new PointClass(92,232),new PointClass(94,233),new PointClass(99,232),new PointClass(102,233),new PointClass(106,233),new PointClass(109,234),new PointClass(117,235),new PointClass(123,236),new PointClass(126,236),new PointClass(135,237),new PointClass(142,238),new PointClass(145,238),new PointClass(152,238),new PointClass(154,239),new PointClass(165,238),new PointClass(174,237),new PointClass(179,236),new PointClass(186,235),new PointClass(191,235),new PointClass(195,233),new PointClass(197,233),new PointClass(200,233),new PointClass(201,235),new PointClass(201,233),new PointClass(199,231),new PointClass(198,226),new PointClass(198,220),new PointClass(196,207),new PointClass(195,195),new PointClass(195,181),new PointClass(195,173),new PointClass(195,163),new PointClass(194,155),new PointClass(192,145),new PointClass(192,143),new PointClass(192,138),new PointClass(191,135),new PointClass(191,133),new PointClass(191,130),new PointClass(190,128),new PointClass(188,129),new PointClass(186,129),new PointClass(181,132),new PointClass(173,131),new PointClass(162,131),new PointClass(151,132),new PointClass(149,132),new PointClass(138,132),new PointClass(136,132),new PointClass(122,131),new PointClass(120,131),new PointClass(109,130),new PointClass(107,130),new PointClass(90,132),new PointClass(81,133),new PointClass(76,133) };
        ArrayList<PointClass> a3 = new ArrayList<>(Arrays.asList(p3));
        System.out.println(a3.size() + "name : rectangle");
        Template tp3 = new Template("rectangle", a3);
        Unistrokes.add(tp3);

        PointClass[] p4 = new PointClass[] { new PointClass(127,141),new PointClass(124,140),new PointClass(120,139),new PointClass(118,139),new PointClass(116,139),new PointClass(111,140),new PointClass(109,141),new PointClass(104,144),new PointClass(100,147),new PointClass(96,152),new PointClass(93,157),new PointClass(90,163),new PointClass(87,169),new PointClass(85,175),new PointClass(83,181),new PointClass(82,190),new PointClass(82,195),new PointClass(83,200),new PointClass(84,205),new PointClass(88,213),new PointClass(91,216),new PointClass(96,219),new PointClass(103,222),new PointClass(108,224),new PointClass(111,224),new PointClass(120,224),new PointClass(133,223),new PointClass(142,222),new PointClass(152,218),new PointClass(160,214),new PointClass(167,210),new PointClass(173,204),new PointClass(178,198),new PointClass(179,196),new PointClass(182,188),new PointClass(182,177),new PointClass(178,167),new PointClass(170,150),new PointClass(163,138),new PointClass(152,130),new PointClass(143,129),new PointClass(140,131),new PointClass(129,136),new PointClass(126,139) };
        ArrayList<PointClass> a4 = new ArrayList<>(Arrays.asList(p4));
        System.out.println(a4.size() + "name : circle");
        Unistrokes.add(new Template("circle", a4));


        PointClass[] p5 = new PointClass[] { new PointClass(91,185),new PointClass(93,185),new PointClass(95,185),new PointClass(97,185),new PointClass(100,188),new PointClass(102,189),new PointClass(104,190),new PointClass(106,193),new PointClass(108,195),new PointClass(110,198),new PointClass(112,201),new PointClass(114,204),new PointClass(115,207),new PointClass(117,210),new PointClass(118,212),new PointClass(120,214),new PointClass(121,217),new PointClass(122,219),new PointClass(123,222),new PointClass(124,224),new PointClass(126,226),new PointClass(127,229),new PointClass(129,231),new PointClass(130,233),new PointClass(129,231),new PointClass(129,228),new PointClass(129,226),new PointClass(129,224),new PointClass(129,221),new PointClass(129,218),new PointClass(129,212),new PointClass(129,208),new PointClass(130,198),new PointClass(132,189),new PointClass(134,182),new PointClass(137,173),new PointClass(143,164),new PointClass(147,157),new PointClass(151,151),new PointClass(155,144),new PointClass(161,137),new PointClass(165,131),new PointClass(171,122),new PointClass(174,118),new PointClass(176,114),new PointClass(177,112),new PointClass(177,114),new PointClass(175,116),new PointClass(173,118) };
        ArrayList<PointClass> a5 = new ArrayList<>(Arrays.asList(p5));
        System.out.println(a5.size() + "name : check");
        Unistrokes.add(new Template("check", a5));


        PointClass[] p6 = new PointClass[] { new PointClass(79,245),new PointClass(79,242),new PointClass(79,239),new PointClass(80,237),new PointClass(80,234),new PointClass(81,232),new PointClass(82,230),new PointClass(84,224),new PointClass(86,220),new PointClass(86,218),new PointClass(87,216),new PointClass(88,213),new PointClass(90,207),new PointClass(91,202),new PointClass(92,200),new PointClass(93,194),new PointClass(94,192),new PointClass(96,189),new PointClass(97,186),new PointClass(100,179),new PointClass(102,173),new PointClass(105,165),new PointClass(107,160),new PointClass(109,158),new PointClass(112,151),new PointClass(115,144),new PointClass(117,139),new PointClass(119,136),new PointClass(119,134),new PointClass(120,132),new PointClass(121,129),new PointClass(122,127),new PointClass(124,125),new PointClass(126,124),new PointClass(129,125),new PointClass(131,127),new PointClass(132,130),new PointClass(136,139),new PointClass(141,154),new PointClass(145,166),new PointClass(151,182),new PointClass(156,193),new PointClass(157,196),new PointClass(161,209),new PointClass(162,211),new PointClass(167,223),new PointClass(169,229),new PointClass(170,231),new PointClass(173,237),new PointClass(176,242),new PointClass(177,244),new PointClass(179,250),new PointClass(181,255),new PointClass(182,257) };
        ArrayList<PointClass> a6 = new ArrayList<>(Arrays.asList(p6));
        System.out.println(a6.size() + "name : caret");
        Unistrokes.add(new Template("caret", a6));


        PointClass[] p7 = new PointClass[] { new PointClass(307,216),new PointClass(333,186),new PointClass(356,215),new PointClass(375,186),new PointClass(399,216),new PointClass(418,186) };
        ArrayList<PointClass> a7 = new ArrayList<>(Arrays.asList(p7));
        System.out.println(a7.size() + "name : zig-zag");
        Unistrokes.add(new Template("zig-zag", a7));


        PointClass[] p8 = new PointClass[] { new PointClass(68,222),new PointClass(70,220),new PointClass(73,218),new PointClass(75,217),new PointClass(77,215),new PointClass(80,213),new PointClass(82,212),new PointClass(84,210),new PointClass(87,209),new PointClass(89,208),new PointClass(92,206),new PointClass(95,204),new PointClass(101,201),new PointClass(106,198),new PointClass(112,194),new PointClass(118,191),new PointClass(124,187),new PointClass(127,186),new PointClass(132,183),new PointClass(138,181),new PointClass(141,180),new PointClass(146,178),new PointClass(154,173),new PointClass(159,171),new PointClass(161,170),new PointClass(166,167),new PointClass(168,167),new PointClass(171,166),new PointClass(174,164),new PointClass(177,162),new PointClass(180,160),new PointClass(182,158),new PointClass(183,156),new PointClass(181,154),new PointClass(178,153),new PointClass(171,153),new PointClass(164,153),new PointClass(160,153),new PointClass(150,154),new PointClass(147,155),new PointClass(141,157),new PointClass(137,158),new PointClass(135,158),new PointClass(137,158),new PointClass(140,157),new PointClass(143,156),new PointClass(151,154),new PointClass(160,152),new PointClass(170,149),new PointClass(179,147),new PointClass(185,145),new PointClass(192,144),new PointClass(196,144),new PointClass(198,144),new PointClass(200,144),new PointClass(201,147),new PointClass(199,149),new PointClass(194,157),new PointClass(191,160),new PointClass(186,167),new PointClass(180,176),new PointClass(177,179),new PointClass(171,187),new PointClass(169,189),new PointClass(165,194),new PointClass(164,196) };
        ArrayList<PointClass> a8 = new ArrayList<>(Arrays.asList(p8));
        System.out.println(a8.size() + "name : arrow");
        Unistrokes.add(new Template("arrow", a8));


        PointClass[] p9 = new PointClass[] { new PointClass(140,124),new PointClass(138,123),new PointClass(135,122),new PointClass(133,123),new PointClass(130,123),new PointClass(128,124),new PointClass(125,125),new PointClass(122,124),new PointClass(120,124),new PointClass(118,124),new PointClass(116,125),new PointClass(113,125),new PointClass(111,125),new PointClass(108,124),new PointClass(106,125),new PointClass(104,125),new PointClass(102,124),new PointClass(100,123),new PointClass(98,123),new PointClass(95,124),new PointClass(93,123),new PointClass(90,124),new PointClass(88,124),new PointClass(85,125),new PointClass(83,126),new PointClass(81,127),new PointClass(81,129),new PointClass(82,131),new PointClass(82,134),new PointClass(83,138),new PointClass(84,141),new PointClass(84,144),new PointClass(85,148),new PointClass(85,151),new PointClass(86,156),new PointClass(86,160),new PointClass(86,164),new PointClass(86,168),new PointClass(87,171),new PointClass(87,175),new PointClass(87,179),new PointClass(87,182),new PointClass(87,186),new PointClass(88,188),new PointClass(88,195),new PointClass(88,198),new PointClass(88,201),new PointClass(88,207),new PointClass(89,211),new PointClass(89,213),new PointClass(89,217),new PointClass(89,222),new PointClass(88,225),new PointClass(88,229),new PointClass(88,231),new PointClass(88,233),new PointClass(88,235),new PointClass(89,237),new PointClass(89,240),new PointClass(89,242),new PointClass(91,241),new PointClass(94,241),new PointClass(96,240),new PointClass(98,239),new PointClass(105,240),new PointClass(109,240),new PointClass(113,239),new PointClass(116,240),new PointClass(121,239),new PointClass(130,240),new PointClass(136,237),new PointClass(139,237),new PointClass(144,238),new PointClass(151,237),new PointClass(157,236),new PointClass(159,237) };
        ArrayList<PointClass> a9 = new ArrayList<>(Arrays.asList(p9));
        System.out.println(a9.size() + "name : left square bracket");
        Unistrokes.add(new Template("left square bracket", a9));


        PointClass[] p10 = new PointClass[] { new PointClass(112,138),new PointClass(112,136),new PointClass(115,136),new PointClass(118,137),new PointClass(120,136),new PointClass(123,136),new PointClass(125,136),new PointClass(128,136),new PointClass(131,136),new PointClass(134,135),new PointClass(137,135),new PointClass(140,134),new PointClass(143,133),new PointClass(145,132),new PointClass(147,132),new PointClass(149,132),new PointClass(152,132),new PointClass(153,134),new PointClass(154,137),new PointClass(155,141),new PointClass(156,144),new PointClass(157,152),new PointClass(158,161),new PointClass(160,170),new PointClass(162,182),new PointClass(164,192),new PointClass(166,200),new PointClass(167,209),new PointClass(168,214),new PointClass(168,216),new PointClass(169,221),new PointClass(169,223),new PointClass(169,228),new PointClass(169,231),new PointClass(166,233),new PointClass(164,234),new PointClass(161,235),new PointClass(155,236),new PointClass(147,235),new PointClass(140,233),new PointClass(131,233),new PointClass(124,233),new PointClass(117,235),new PointClass(114,238),new PointClass(112,238) };
        ArrayList<PointClass> a10 = new ArrayList<>(Arrays.asList(p10));
        System.out.println(a10.size() + "name : right square bracket");
        Unistrokes.add(new Template("right square bracket", a10));


        PointClass[] p11 = new PointClass[] { new PointClass(89,164),new PointClass(90,162),new PointClass(92,162),new PointClass(94,164),new PointClass(95,166),new PointClass(96,169),new PointClass(97,171),new PointClass(99,175),new PointClass(101,178),new PointClass(103,182),new PointClass(106,189),new PointClass(108,194),new PointClass(111,199),new PointClass(114,204),new PointClass(117,209),new PointClass(119,214),new PointClass(122,218),new PointClass(124,222),new PointClass(126,225),new PointClass(128,228),new PointClass(130,229),new PointClass(133,233),new PointClass(134,236),new PointClass(136,239),new PointClass(138,240),new PointClass(139,242),new PointClass(140,244),new PointClass(142,242),new PointClass(142,240),new PointClass(142,237),new PointClass(143,235),new PointClass(143,233),new PointClass(145,229),new PointClass(146,226),new PointClass(148,217),new PointClass(149,208),new PointClass(149,205),new PointClass(151,196),new PointClass(151,193),new PointClass(153,182),new PointClass(155,172),new PointClass(157,165),new PointClass(159,160),new PointClass(162,155),new PointClass(164,150),new PointClass(165,148),new PointClass(166,146) };
        ArrayList<PointClass> a11 = new ArrayList<>(Arrays.asList(p11));
        System.out.println(a11.size() + "name : v");
        Unistrokes.add(new Template("v", a11));


        PointClass[] p12 = new PointClass[] { new PointClass(123,129),new PointClass(123,131),new PointClass(124,133),new PointClass(125,136),new PointClass(127,140),new PointClass(129,142),new PointClass(133,148),new PointClass(137,154),new PointClass(143,158),new PointClass(145,161),new PointClass(148,164),new PointClass(153,170),new PointClass(158,176),new PointClass(160,178),new PointClass(164,183),new PointClass(168,188),new PointClass(171,191),new PointClass(175,196),new PointClass(178,200),new PointClass(180,202),new PointClass(181,205),new PointClass(184,208),new PointClass(186,210),new PointClass(187,213),new PointClass(188,215),new PointClass(186,212),new PointClass(183,211),new PointClass(177,208),new PointClass(169,206),new PointClass(162,205),new PointClass(154,207),new PointClass(145,209),new PointClass(137,210),new PointClass(129,214),new PointClass(122,217),new PointClass(118,218),new PointClass(111,221),new PointClass(109,222),new PointClass(110,219),new PointClass(112,217),new PointClass(118,209),new PointClass(120,207),new PointClass(128,196),new PointClass(135,187),new PointClass(138,183),new PointClass(148,167),new PointClass(157,153),new PointClass(163,145),new PointClass(165,142),new PointClass(172,133),new PointClass(177,127),new PointClass(179,127),new PointClass(180,125) };
        ArrayList<PointClass> a12 = new ArrayList<>(Arrays.asList(p12));
        System.out.println(a12.size() + "name : delete");
        Unistrokes.add(new Template("delete", a12));


        PointClass[] p13 = new PointClass[] { new PointClass(150,116),new PointClass(147,117),new PointClass(145,116),new PointClass(142,116),new PointClass(139,117),new PointClass(136,117),new PointClass(133,118),new PointClass(129,121),new PointClass(126,122),new PointClass(123,123),new PointClass(120,125),new PointClass(118,127),new PointClass(115,128),new PointClass(113,129),new PointClass(112,131),new PointClass(113,134),new PointClass(115,134),new PointClass(117,135),new PointClass(120,135),new PointClass(123,137),new PointClass(126,138),new PointClass(129,140),new PointClass(135,143),new PointClass(137,144),new PointClass(139,147),new PointClass(141,149),new PointClass(140,152),new PointClass(139,155),new PointClass(134,159),new PointClass(131,161),new PointClass(124,166),new PointClass(121,166),new PointClass(117,166),new PointClass(114,167),new PointClass(112,166),new PointClass(114,164),new PointClass(116,163),new PointClass(118,163),new PointClass(120,162),new PointClass(122,163),new PointClass(125,164),new PointClass(127,165),new PointClass(129,166),new PointClass(130,168),new PointClass(129,171),new PointClass(127,175),new PointClass(125,179),new PointClass(123,184),new PointClass(121,190),new PointClass(120,194),new PointClass(119,199),new PointClass(120,202),new PointClass(123,207),new PointClass(127,211),new PointClass(133,215),new PointClass(142,219),new PointClass(148,220),new PointClass(151,221) };
        ArrayList<PointClass> a13 = new ArrayList<>(Arrays.asList(p13));
        System.out.println(a13.size() + "name : left curly brace");
        Unistrokes.add(new Template("left curly brace", a13));


        PointClass[] p14 = new PointClass[] { new PointClass(117,132),new PointClass(115,132),new PointClass(115,129),new PointClass(117,129),new PointClass(119,128),new PointClass(122,127),new PointClass(125,127),new PointClass(127,127),new PointClass(130,127),new PointClass(133,129),new PointClass(136,129),new PointClass(138,130),new PointClass(140,131),new PointClass(143,134),new PointClass(144,136),new PointClass(145,139),new PointClass(145,142),new PointClass(145,145),new PointClass(145,147),new PointClass(145,149),new PointClass(144,152),new PointClass(142,157),new PointClass(141,160),new PointClass(139,163),new PointClass(137,166),new PointClass(135,167),new PointClass(133,169),new PointClass(131,172),new PointClass(128,173),new PointClass(126,176),new PointClass(125,178),new PointClass(125,180),new PointClass(125,182),new PointClass(126,184),new PointClass(128,187),new PointClass(130,187),new PointClass(132,188),new PointClass(135,189),new PointClass(140,189),new PointClass(145,189),new PointClass(150,187),new PointClass(155,186),new PointClass(157,185),new PointClass(159,184),new PointClass(156,185),new PointClass(154,185),new PointClass(149,185),new PointClass(145,187),new PointClass(141,188),new PointClass(136,191),new PointClass(134,191),new PointClass(131,192),new PointClass(129,193),new PointClass(129,195),new PointClass(129,197),new PointClass(131,200),new PointClass(133,202),new PointClass(136,206),new PointClass(139,211),new PointClass(142,215),new PointClass(145,220),new PointClass(147,225),new PointClass(148,231),new PointClass(147,239),new PointClass(144,244),new PointClass(139,248),new PointClass(134,250),new PointClass(126,253),new PointClass(119,253),new PointClass(115,253) };
        ArrayList<PointClass> a14 = new ArrayList<>(Arrays.asList(p14));
        System.out.println(a14.size() + "name : right curly brace");
        Unistrokes.add(new Template("right curly brace", a14));


        PointClass[] p15 = new PointClass[] { new PointClass(75,250),new PointClass(75,247),new PointClass(77,244),new PointClass(78,242),new PointClass(79,239),new PointClass(80,237),new PointClass(82,234),new PointClass(82,232),new PointClass(84,229),new PointClass(85,225),new PointClass(87,222),new PointClass(88,219),new PointClass(89,216),new PointClass(91,212),new PointClass(92,208),new PointClass(94,204),new PointClass(95,201),new PointClass(96,196),new PointClass(97,194),new PointClass(98,191),new PointClass(100,185),new PointClass(102,178),new PointClass(104,173),new PointClass(104,171),new PointClass(105,164),new PointClass(106,158),new PointClass(107,156),new PointClass(107,152),new PointClass(108,145),new PointClass(109,141),new PointClass(110,139),new PointClass(112,133),new PointClass(113,131),new PointClass(116,127),new PointClass(117,125),new PointClass(119,122),new PointClass(121,121),new PointClass(123,120),new PointClass(125,122),new PointClass(125,125),new PointClass(127,130),new PointClass(128,133),new PointClass(131,143),new PointClass(136,153),new PointClass(140,163),new PointClass(144,172),new PointClass(145,175),new PointClass(151,189),new PointClass(156,201),new PointClass(161,213),new PointClass(166,225),new PointClass(169,233),new PointClass(171,236),new PointClass(174,243),new PointClass(177,247),new PointClass(178,249),new PointClass(179,251),new PointClass(180,253),new PointClass(180,255),new PointClass(179,257),new PointClass(177,257),new PointClass(174,255),new PointClass(169,250),new PointClass(164,247),new PointClass(160,245),new PointClass(149,238),new PointClass(138,230),new PointClass(127,221),new PointClass(124,220),new PointClass(112,212),new PointClass(110,210),new PointClass(96,201),new PointClass(84,195),new PointClass(74,190),new PointClass(64,182),new PointClass(55,175),new PointClass(51,172),new PointClass(49,170),new PointClass(51,169),new PointClass(56,169),new PointClass(66,169),new PointClass(78,168),new PointClass(92,166),new PointClass(107,164),new PointClass(123,161),new PointClass(140,162),new PointClass(156,162),new PointClass(171,160),new PointClass(173,160),new PointClass(186,160),new PointClass(195,160),new PointClass(198,161),new PointClass(203,163),new PointClass(208,163),new PointClass(206,164),new PointClass(200,167),new PointClass(187,172),new PointClass(174,179),new PointClass(172,181),new PointClass(153,192),new PointClass(137,201),new PointClass(123,211),new PointClass(112,220),new PointClass(99,229),new PointClass(90,237),new PointClass(80,244),new PointClass(73,250),new PointClass(69,254),new PointClass(69,252) };
        ArrayList<PointClass> a15 = new ArrayList<>(Arrays.asList(p15));
        System.out.println(a15.size() + "name : star");
        Unistrokes.add(new Template("star", a15));


        PointClass[] p16 = new PointClass[] { new PointClass(81,219),new PointClass(84,218),new PointClass(86,220),new PointClass(88,220),new PointClass(90,220),new PointClass(92,219),new PointClass(95,220),new PointClass(97,219),new PointClass(99,220),new PointClass(102,218),new PointClass(105,217),new PointClass(107,216),new PointClass(110,216),new PointClass(113,214),new PointClass(116,212),new PointClass(118,210),new PointClass(121,208),new PointClass(124,205),new PointClass(126,202),new PointClass(129,199),new PointClass(132,196),new PointClass(136,191),new PointClass(139,187),new PointClass(142,182),new PointClass(144,179),new PointClass(146,174),new PointClass(148,170),new PointClass(149,168),new PointClass(151,162),new PointClass(152,160),new PointClass(152,157),new PointClass(152,155),new PointClass(152,151),new PointClass(152,149),new PointClass(152,146),new PointClass(149,142),new PointClass(148,139),new PointClass(145,137),new PointClass(141,135),new PointClass(139,135),new PointClass(134,136),new PointClass(130,140),new PointClass(128,142),new PointClass(126,145),new PointClass(122,150),new PointClass(119,158),new PointClass(117,163),new PointClass(115,170),new PointClass(114,175),new PointClass(117,184),new PointClass(120,190),new PointClass(125,199),new PointClass(129,203),new PointClass(133,208),new PointClass(138,213),new PointClass(145,215),new PointClass(155,218),new PointClass(164,219),new PointClass(166,219),new PointClass(177,219),new PointClass(182,218),new PointClass(192,216),new PointClass(196,213),new PointClass(199,212),new PointClass(201,211) };
        ArrayList<PointClass> a16 = new ArrayList<>(Arrays.asList(p16));
        System.out.println(a16.size() + "name : pigtail");
        Unistrokes.add(new Template("pigtail", a16));

    }
*/
    //Recognize method which returns the N- Best List for each gesture
public List<Result> Recognize(ArrayList<PointClass> points,ArrayList<GesStructure> Unistrokes, boolean Protractor, String user) {
    //System.out.println("Start of Recognize");
    //System.out.println("Original Size before Preprocessing: " + points.size());
    Template candidate = new Template("",points);
    //System.out.println("Original Size after Preprocessing: " + candidate.Points.size());
    //Instant before = Instant.now();
    List<Result> bestList=new ArrayList<>();
    double bestScore =0.0;
    int u = -1;
    int insno = 0;
    double b = Double.POSITIVE_INFINITY;
    double AngleRange = Deg2Rad(45.0);
    double AnglePrecision = Deg2Rad(2.0);
    double d;
    DecimalFormat df = new DecimalFormat("#.##");
    //System.out.println("Number of templates :" + Unistrokes.size());
    for (int i = 0; i < Unistrokes.size(); i++) {
        Template t = new Template(Unistrokes.get(i).label, Unistrokes.get(i).points);
        if(Protractor){
            d = OptimalCosineDistance(t.Vector, candidate.Vector);
        }
        else {
            d = DistanceAtBestAngle(candidate.Points, t, -AngleRange, +AngleRange, AnglePrecision);
        }
        double score =  (1.0 - d);
        score = Double.parseDouble(df.format(score));
        int insnumber=Unistrokes.get(i).InstanceNumber;
        bestList.add(new Result(Unistrokes.get(i).label, score, user, insnumber));
        if (d < b) {
            b = d;
            u = i;
            insno=Unistrokes.get(u).InstanceNumber;
            bestScore=score;
        }
    }
    //Instant after = Instant.now();
    //long timedif = Duration.between(before, after).toMillis();
    //System.out.println("End of Recognize");
    //bestResult= u == -1 ? new Result("No match.", 0.0, user,insno) : new Result(Unistrokes.get(u).label, Protractor ? (1.0 - b) : bestScore, user, insno);
    Collections.sort(bestList, new Comparator<Result>() {
        public int compare(Result r1, Result r2) {
            return Double.compare(r2.getScore(), r1.getScore());
        }
    });


    int minimum = Math.min(bestList.size(),50);
    List<Result> nbestlist = bestList.subList(0,minimum);
    bestResult =nbestlist.get(0);
    return nbestlist;
}
    // methods needed for the Golden Section Search
    public double Deg2Rad(double d) {
        return (d * Math.PI / 180.0);
    }

    public double PathDistance(ArrayList<PointClass> pts1, ArrayList<PointClass> pts2) {
        double d = 0.0;
        for (int i = 0; i < pts1.size(); i++) // assumes pts1.length == pts2.length
            d += Distance(pts1.get(i), pts2.get(i));
        return d /(double) pts1.size();
    }

    public double DistanceAtAngle(ArrayList<PointClass> points, Template T, double radians) {
        ArrayList<PointClass> newPoints = RotateBy(points, radians);
        return PathDistance(newPoints, T.Points);
    }

    public double DistanceAtBestAngle(ArrayList<PointClass> points, Template T, double a, double b, double threshold) {
        double x1 = Phi * a + (1.0 - Phi) * b;
        double f1 = DistanceAtAngle(points, T, x1);
        double x2 = (1.0 - Phi) * a + Phi * b;
        double f2 = DistanceAtAngle(points, T, x2);
        while (Math.abs(b - a) > threshold) {
            if (f1 < f2) {
                b = x2;
                x2 = x1;
                f2 = f1;
                x1 = Phi * a + (1.0 - Phi) * b;
                f1 = DistanceAtAngle(points, T, x1);
            } else {
                a = x1;
                x1 = x2;
                f1 = f2;
                x2 = (1.0 - Phi) * a + Phi * b;
                f2 = DistanceAtAngle(points, T, x2);
            }
        }
        return Math.min(f1, f2);
    }

    public double Distance(PointClass p1, PointClass p2) {
        double dx = p2.getX() - p1.getX();
        double dy = p2.getY() - p1.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
    // method needed for Protractor
    private double OptimalCosineDistance(double[] v1, double[] v2) {
        double a = 0.0;
        double b = 0.0;
        double angle;

        int len = Math.min(v1.length, v2.length);
        for (int i = 0; i < len; i += 2) {
            a += v1[i] * v2[i] + v1[i + 1] * v2[i + 1];
            b += v1[i] * v2[i + 1] - v1[i + 1] * v2[i];
        }

        angle = Math.atan(b / a);
        return Math.acos(a * Math.cos(angle) + b * Math.sin(angle));
    }

}
