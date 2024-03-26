/**
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.guing.common.components.dialogs;

import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.opentcs.guing.base.components.properties.type.ModelAttribute;
import org.opentcs.guing.base.components.properties.type.Property;

/**
 * A dialog in which a {@link DialogContent} can be added.
 * The dialog has an OK and a cancel button.
 */
public class StandardDetailsDialog
    extends javax.swing.JDialog
    implements DetailsDialog {

  /**
   * A return status code - returned if Cancel button has been pressed
   */
  public static final int RET_CANCEL = 0;
  /**
   * A return status code - returned if OK button has been pressed
   */
  public static final int RET_OK = 1;
  private int returnStatus = RET_CANCEL;
  /**
   * The details dialog content to change a property.
   */
  private final DetailsDialogContent fContent;
  private final Component fParentComponent;

  /**
   * Creates new form JDialog
   *
   * @param parent The parent component.
   * @param content Details dialog content.
   * @param modal Whether or not the dialog is modal.
   */
  public StandardDetailsDialog(JPanel parent, boolean modal, DetailsDialogContent content) {
    super(JOptionPane.getFrameForComponent(parent), modal);
    fContent = content;
    fParentComponent = parent;
    initialize();
  }

  /**
   * Creates a new dialog.
   *
   * @param parent The parent dialog.
   * @param modal Whether or not the dialog is modal.
   * @param content Details dialog content.
   */
  public StandardDetailsDialog(JDialog parent, boolean modal, DetailsDialogContent content) {
    super(parent, modal);
    fContent = content;
    fParentComponent = parent;
    initialize();
  }

  /*
   * Initialises the dialog.
   */
  protected final void initialize() {
    JComponent component = (JComponent) fContent;
    component.setBorder(new EmptyBorder(new java.awt.Insets(5, 5, 5, 5)));
    getContentPane().add(component, java.awt.BorderLayout.CENTER);
    initComponents();
    setTitle(fContent.getTitle());
    activate();
  }

  @Override
  public void activate() {
    getRootPane().setDefaultButton(okButton);
    pack();
    setLocationRelativeTo(fParentComponent);
  }

  public Component getParentComponent() {
    return fParentComponent;
  }

  /**
   * Returns the return status.
   *
   * @return the return status of this dialog - one of RET_OK or RET_CANCEL
   */
  public int getReturnStatus() {
    return returnStatus;
  }

  // CHECKSTYLE:OFF
  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    controlPanel = new javax.swing.JPanel();
    buttonPanel = new javax.swing.JPanel();
    okButton = new javax.swing.JButton();
    cancelButton = new CancelButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setIconImage(null);
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        closeDialog(evt);
      }
    });

    controlPanel.setLayout(new java.awt.BorderLayout());

    buttonPanel.setOpaque(false);

    okButton.setFont(okButton.getFont().deriveFont(okButton.getFont().getStyle() | java.awt.Font.BOLD));
    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("i18n/org/opentcs/plantoverview/system"); // NOI18N
    okButton.setText(bundle.getString("standardDetailsDialog.button_ok.text")); // NOI18N
    okButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        okButtonActionPerformed(evt);
      }
    });
    buttonPanel.add(okButton);

    cancelButton.setFont(cancelButton.getFont());
    cancelButton.setText(bundle.getString("standardDetailsDialog.button_cancel.text")); // NOI18N
    cancelButton.setOpaque(false);
    cancelButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cancelButtonActionPerformed(evt);
      }
    });
    buttonPanel.add(cancelButton);

    controlPanel.add(buttonPanel, java.awt.BorderLayout.SOUTH);

    getContentPane().add(controlPanel, java.awt.BorderLayout.SOUTH);

    pack();
  }// </editor-fold>//GEN-END:initComponents
  // CHECKSTYLE:ON

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
      doClose(RET_CANCEL);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
      fContent.updateValues();

      Property property = fContent.getProperty();

      if (property != null) {
        property.setChangeState(ModelAttribute.ChangeState.DETAIL_CHANGED);
      }

      doClose(RET_OK);
    }//GEN-LAST:event_okButtonActionPerformed

  /**
   * Closes the dialog.
   */
  private void doClose(int retStatus) {
    returnStatus = retStatus;

    setVisible(false);
  }

    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
      doClose(RET_CANCEL);
    }//GEN-LAST:event_closeDialog

  @Override
  public DetailsDialogContent getDialogContent() {
    return fContent;
  }

  // CHECKSTYLE:OFF
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel buttonPanel;
  private javax.swing.JButton cancelButton;
  private javax.swing.JPanel controlPanel;
  private javax.swing.JButton okButton;
  // End of variables declaration//GEN-END:variables
  // CHECKSTYLE:ON
}