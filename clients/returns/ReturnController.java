package clients.returns;


/**
 * The Cashier Controller
 */

public class ReturnController
{
  private ReturnModel model = null;
  private ReturnView  view  = null;

  /**
   * Constructor
   * @param model The model 
   * @param view  The view from which the interaction came
   */
  public ReturnController( ReturnModel model, ReturnView view )
  {
    this.view  = view;
    this.model = model;
  }

  /**
   * Check interaction from view
   * @param pn The product number to be checked
   */
  public void doReturn( String pn )
  {
    model.doReturn(pn);
  }


}
