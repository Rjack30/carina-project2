package com.qaprosoft.carina.demo

import com.qaprosoft.carina.core.foundation.IAbstractTest
import com.qaprosoft.carina.core.foundation.metascore.annotations.XlsDataSourceParameters
import com.qaprosoft.carina.core.foundation.report.testrail.TestRailCases
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner
import com.qaprosoft.carina.core.foundation.utils.tag.Priority
import com.qaprosoft.carina.core.foundation.utils.tag.TestPriority
import org.testng.Assert
import org.testng.annotations.Parameters
import org.testng.annotations.Test


class MetaScoreRatingTest implements IAbstractTest {
        @Test(dataProvider = "MetaScore")
        @MethodOwner(owner = "qpsdemo")
        @TestRailCases(testCasesId = "1")
        @XlsDataSourceParameters(path = "xls/demo.xlsx", sheet = "Calculator", dsUid = "TUID", dsArgs = "a,b,c", testRailColumn = "a")
        void testSumOperation(String a, String b, String c) {
            int actual = Integer.valueOf(a) + Integer.valueOf(b)
            int expected = Integer.valueOf(c)
            Assert.assertEquals(actual, expected, "Invalid sum result!")
        }


        @Test(dataProvider = "MetaScore")
        @MethodOwner(owner = "qpsdemo")
        @TestRailCases(testCasesId = "1")
        @XlsDataSourceParameters(path = "xls/demo.xlsx", sheet = "Calculator", dsUid = "TUID", testRailColumn = "a")
        void testSumOperationEx(HashMap<String, String> args) {
            int actual = Integer.valueOf(args.get("a")) + Integer.valueOf(args.get("b"))
            int expected = Integer.valueOf(args.get("c"))
            Assert.assertEquals(actual, expected, "Invalid sum result!")
        }


        @Test(MetaScore= "MS1")
        @MethodOwner(owner = "qpsdemo")
        @TestPriority(Priority.P3)
        @TestRailCases(testCasesId = "44")
        private
        void testMuliplyOperation(String testRailColumn, int a, int b, int c) {
            setCases(testRailColumn.split(","))
            int actual = a * b
            int expected = c
            Assert.assertEquals(actual, expected, "Invalid sum result!")
        }

        @MetaScoreRatingTest(parallel = false, name = "DP1")
        static Object[][] metascorerating() {
            return [
                    { "TUID: Data1"  "85,100" , 2 , 3 , 6 },
                    { "TUID: Data2" "75" , 6 , 6 , 36 },
                    { "TUID: Data3"  "66" , 5 , 8 , 40 }]
        }


        @Test()
        @MethodOwner(owner = "qpsdemo")
        @Parameters(value = { "a" "b" "c" })
        @TestRailCases(testCasesId = "55")
        void testSubstractOperation(int a, int b, int c) {
            int actual = Integer.valueOf(a) - Integer.valueOf(b)
            int expected = Integer.valueOf(c)
            Assert.assertEquals(actual, expected, "Invalid substract result!")
        }

    }




