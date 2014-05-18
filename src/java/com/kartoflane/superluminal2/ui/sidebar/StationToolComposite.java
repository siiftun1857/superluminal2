package com.kartoflane.superluminal2.ui.sidebar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.kartoflane.superluminal2.core.Cache;
import com.kartoflane.superluminal2.core.Manager;
import com.kartoflane.superluminal2.tools.StationTool;
import com.kartoflane.superluminal2.ui.DirectionCombo;

public class StationToolComposite extends Composite {

	private StationTool tool = null;
	private Label lblHelp;
	private Label lblPlaceHelp;
	private Label lblDirHelp;
	private Label lblRemHelp;

	public StationToolComposite(Composite parent) {
		super(parent, SWT.NONE);
		setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		setLayout(new GridLayout(2, false));

		tool = (StationTool) Manager.getSelectedTool();
		Image helpImage = Cache.checkOutImage(this, "cpath:/assets/help.png");

		Label lblRoomTool = new Label(this, SWT.NONE);
		lblRoomTool.setAlignment(SWT.CENTER);
		lblRoomTool.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		lblRoomTool.setText("Station Placement Tool");

		lblHelp = new Label(this, SWT.NONE);
		lblHelp.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblHelp.setImage(helpImage);
		StringBuilder buf = new StringBuilder();
		buf.append("When Placement option is selected:\n");
		buf.append("- Holding down Shift while left-clicking changes\n");
		buf.append("  the direction of the station.\n");
		buf.append("- Right-clicking removes the station.");
		lblHelp.setToolTipText(buf.toString());

		Label separator = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		separator.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 2, 1));

		Button btnPlace = new Button(this, SWT.RADIO);
		btnPlace.setText("Placement");

		lblPlaceHelp = new Label(this, SWT.NONE);
		lblPlaceHelp.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPlaceHelp.setImage(helpImage);
		lblPlaceHelp.setToolTipText("Allows you to change position of the station.");

		Button btnDirection = new Button(this, SWT.RADIO);
		btnDirection.setText("Direction");

		lblDirHelp = new Label(this, SWT.NONE);
		lblDirHelp.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDirHelp.setImage(helpImage);
		lblDirHelp.setToolTipText("Allows you to change the station's facing.");

		final DirectionCombo cmbDirection = new DirectionCombo(this, SWT.READ_ONLY, false);
		GridData gd_cmbDirection = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 2, 1);
		gd_cmbDirection.widthHint = 80;
		cmbDirection.setLayoutData(gd_cmbDirection);
		cmbDirection.select(DirectionCombo.toIndex(tool.getDirection()));
		cmbDirection.setEnabled(tool.isStateDirection());

		Button btnRemove = new Button(this, SWT.RADIO);
		btnRemove.setText("Removal");

		lblRemHelp = new Label(this, SWT.NONE);
		lblRemHelp.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRemHelp.setImage(helpImage);
		lblRemHelp.setToolTipText("Allows you to remove the station.\nRemoving the station means that crew\nwill not be able to man the system.");

		cmbDirection.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tool.setDirection(cmbDirection.getDirection());
			}
		});

		btnPlace.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cmbDirection.setEnabled(false);
				tool.setStatePlacement();
			}
		});
		btnDirection.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cmbDirection.setEnabled(true);
				tool.setStateDirection();
			}
		});
		btnRemove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cmbDirection.setEnabled(false);
				tool.setStateRemoval();
			}
		});

		btnPlace.setSelection(tool.isStatePlacement());
		btnDirection.setSelection(tool.isStateDirection());
		btnRemove.setSelection(tool.isStateRemoval());

		pack();
	}

	@Override
	public void dispose() {
		super.dispose();
		Cache.checkInImage(this, "cpath:/assets/help.png");
	}
}
