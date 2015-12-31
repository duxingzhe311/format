package format.frame;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import format.tool.JsonFormatTool;


public class MainFrame {  
  private Shell shell;
  private Shell window;
  private Display display;
  
  private Text textSrc;
  private Text textDest;
  
  private Button btnTransform;
  private Button btnClear;

  public MainFrame(Shell shell) {
    this.shell = shell;
    this.window = shell.getShell();
    this.display = shell.getDisplay();
  }

  public void show() {
    shell.setText("JSON格式化");
    Rectangle rect = window.getBounds();
    shell.setBounds(rect.width / 2 - 100, rect.height / 2 - 200, rect.width / 2, rect.height / 4 * 3);
    Font font = new Font(display, "Arial", 14, SWT.BOLD | SWT.ITALIC);

    GridLayout gridLayOut = new GridLayout();
    gridLayOut.numColumns = 2;
    gridLayOut.verticalSpacing = 8;
    shell.setLayout(gridLayOut);

    
    textSrc = new Text(shell,SWT.LEFT | SWT.MULTI);
    textSrc.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
    textSrc.addModifyListener(new ModifyListener() {
      @Override
      public void modifyText(ModifyEvent e) {
        String src = textSrc.getText();
        textDest.setText("");
        if(null != src && !"".equals(src = src.trim())){
          String str = JsonFormatTool.formatJson(src);
          textDest.setText(str);
        }
      }
    });
    textDest = new Text(shell,SWT.LEFT | SWT.MULTI | SWT.V_SCROLL);
    textDest.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
    
   
    btnClear = new Button(shell, SWT.LEFT);
    btnClear.setFont(font);
    btnClear.setText("清空");
    btnClear.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        if(!"".equals(textSrc.getText()))
          textSrc.setText("");
        if(!"".equals(textDest.getText()))
          textDest.setText("");
      }
    });
    
    
    btnTransform = new Button(shell, SWT.LEFT);
    btnTransform.setFont(font);
    btnTransform.setText("退出");
    btnTransform.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        System.exit(0);
      }
    });
    
    
    
    shell.open();

//    while (!shell.isDisposed()) {
//      if (!display.readAndDispatch())
//        display.sleep();
//    }
//    display.dispose();
  }

  public static void main(String[] args) {
    new MainFrame(new Shell(new Display())).show();
  }

}
