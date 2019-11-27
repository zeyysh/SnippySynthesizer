import ast._
import org.junit.Test
import org.junit.Assert._
import org.scalatest.junit.JUnitSuite


class ASTTests extends JUnitSuite{
  @Test def literalNode(): Unit = {
    val literalNode: ASTNode = new Literal("1")
    assertEquals("1",literalNode.name)
    assertEquals("1", literalNode.code)
  }

  @Test def variableNode(): Unit = {
    val varNode: ASTNode = new Variable("x")
    assertEquals("x",varNode.name)
    assertEquals("x", varNode.code)
  }

  @Test def binaryOpNode(): Unit = {
    val binOpNode: ASTNode = new BinOperator("+", new Literal("1"), new Literal("'b'"))
    assertEquals("+",binOpNode.name)
    assertEquals("1 + 'b'", binOpNode.code)

    val binOpNode2 = new BinOperator("*", new Literal("2"), binOpNode)
    assertEquals("*",binOpNode2.name)
    assertEquals("2 * (1 + 'b')", binOpNode2.code)
  }

  @Test def funcCallNode(): Unit = {
    val funcNode: ASTNode = new FunctionCall("foo",0, List())
    assertEquals("foo",funcNode.name)
    assertEquals("foo()", funcNode.code)

    val funcNode2 = new FunctionCall("bar",2,List(new Literal("1"),new Variable("x")))
    assertEquals("bar", funcNode2.name)
    assertEquals("bar(1,x)",funcNode2.code)
  }

  @Test def lambdaNode(): Unit = {
    val lambdaNode: ASTNode = new Lambda(List(), new Literal("False"))
    assertEquals("()", lambdaNode.name)
    assertEquals("lambda : False", lambdaNode.code)

    val x = new Variable("x")
    val y = new Variable("y")
    val lambdaNode2: ASTNode = new Lambda(List(x, y), new BinOperator("+",x,y))
    assertEquals("(x,y)",lambdaNode2.name)
    assertEquals("lambda x,y: x + y", lambdaNode2.code)
  }
}