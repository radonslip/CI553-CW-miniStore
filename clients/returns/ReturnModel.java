package clients.returns;

import catalogue.Basket;
import catalogue.Product;
import debug.DEBUG;
import middle.*;

import java.util.Observable;

/**
 * Implements the Model of the cashier client
 */
public class ReturnModel extends Observable
{
  private enum State { process, checked }

  private State       theState   = State.process;   // Current state
  private Product     theProduct = null;            // Current product
  private Basket      theBasket  = null;            // Bought items

  private String      pn = "";                      // Product being processed

  private StockReadWriter theStock     = null;
  private OrderProcessing theOrder     = null;

  /**
   * Construct the model of the Cashier
   * @param mf The factory to create the connection objects
   */

  public ReturnModel(MiddleFactory mf)
  {
    try                                           // 
    {      
      theStock = mf.makeStockReadWriter();        // Database access
      theOrder = mf.makeOrderProcessing();        // Process order
    } catch ( Exception e )
    {
      DEBUG.error("CashierModel.constructor\n%s", e.getMessage() );
    }
    theState   = State.process;                  // Current state
  }
  
  /**
   * Get the Basket of products
   * @return basket
   */
  public Basket getBasket()
  {
    return theBasket;
  }

  /**
   * Return an item to the store
   * @param productNum The product number of the item to be returned to the store
   */
  public void doReturn(String productNum)
  {
    // Setup
    String theAction = "";
    theBasket = makeBasket();
    pn  = productNum.trim();
    String pn  = productNum.trim(); 

    try
    {
      // If the id exists return the item to the store
      if ( theStock.exists( pn ) )
      {
        theStock.addStock(pn, 1);
        Product pr = theStock.getDetails(pn);
        theBasket.add(pr);
        theAction = "";
      } else {
        theAction =
          "Unknown product number " + pn;
      } 
    } catch( StockException e ) 
    {
      theAction = e.getMessage();
    }
    setChanged(); notifyObservers(theAction);
  }
  


  /**
   * return an instance of a new Basket
   * @return an instance of a new Basket
   */
  protected Basket makeBasket()
  {
    return new Basket();
  }
}
  
