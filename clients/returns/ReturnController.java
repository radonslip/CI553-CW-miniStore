package clients.returns;


/**
 * The Return Controller
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
   * Return an item to the store
   * @param pn The product number of the item to be returned
   */
  public void doReturn( String pn )
  {
    model.doReturn(pn);
  }


}
