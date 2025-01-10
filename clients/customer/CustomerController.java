package clients.customer;

/**
 * The Customer Controller
 */

public class CustomerController
{
  private CustomerModel model = null;
  private CustomerView  view  = null;

  /**
   * Constructor
   * @param model The model 
   * @param view  The view from which the interaction came
   */
  public CustomerController( CustomerModel model, CustomerView view )
  {
    this.view  = view;
    this.model = model;
  }

  /**
   * Check interaction from view
   * @param pn The product number to be checked
   */
  public void doCheck( String pn )
  {
    model.doCheck(pn);
  }

  /**
   * Takes customers text search, sanitises it and searches for a corresponding ID before running that through a normal id check
   * @param name The unsanitised user input
   */

  public void doNameCheck(String name)
  {
    // Sanitize the entered name
    String sanitizedName = name.toLowerCase();
    sanitizedName = sanitizedName.replaceAll("\\s+", "");

    String id;

    // Search for a corresponding id
    switch (sanitizedName) 
    {
      case "tv":
        id = "0001";
        break;

      case "radio":
        id = "0002";
        break;

      case "toaster":
        id = "0003";
        break;

      case "watch":
        id = "0004";
        break;

      case "camera":
        id = "0005";
        break;

      case "musicplayer":
        id = "0006";
        break;

      case "usbdriver":
        id = "0007";
        break;
    
      default:
        id="0000";
        break;
    }

    // Run a normal id check
    model.doCheck(id);
  }

  /**
   * Clear interaction from view
   */
  public void doClear()
  {
    model.doClear();
  }

  
}

