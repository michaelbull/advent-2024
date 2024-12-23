package com.github.michaelbull.advent2024.day23

import com.github.michaelbull.advent2024.day23.Day23.part1
import com.github.michaelbull.advent2024.day23.Day23.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day23Test {

    @Test
    fun `example 1`() {
        val input = """
            kh-tc
            qp-kh
            de-cg
            ka-co
            yn-aq
            qp-ub
            cg-tb
            vc-aq
            tb-ka
            wh-tc
            yn-cg
            kh-ub
            ta-co
            de-co
            tc-td
            tb-wq
            wh-td
            ta-ka
            td-qp
            aq-cg
            wq-ub
            ub-vc
            de-ta
            wq-aq
            wq-vc
            wh-yn
            ka-de
            kh-ta
            co-tc
            wh-qp
            tb-vc
            td-yn
        """

        assertEquals("7", Day23.solve(::part1, input))
    }

    @Test
    fun `example 2`() {
        val input = """
            kh-tc
            qp-kh
            de-cg
            ka-co
            yn-aq
            qp-ub
            cg-tb
            vc-aq
            tb-ka
            wh-tc
            yn-cg
            kh-ub
            ta-co
            de-co
            tc-td
            tb-wq
            wh-td
            ta-ka
            td-qp
            aq-cg
            wq-ub
            ub-vc
            de-ta
            wq-aq
            wq-vc
            wh-yn
            ka-de
            kh-ta
            co-tc
            wh-qp
            tb-vc
            td-yn
        """

        assertEquals("co,de,ka,ta", Day23.solve(::part2, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals("1348", Day23.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals("am,bv,ea,gh,is,iy,ml,nj,nl,no,om,tj,yv", Day23.solve(::part2))
    }
}
