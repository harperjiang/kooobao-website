/**
 * Modified from Ext.ux.layout.Center
 */
Ext.define('Kooobao.layout.Center',
		{
			extend : 'Ext.layout.container.Fit',
			alias : 'layout.kooobao.center',

			percentRe : /^\d+(?:\.\d+)?\%$/,

			itemCls : 'ux-layout-center-item',

			initLayout : function() {
				this.callParent(arguments);

				this.owner.addCls('ux-layout-center');
			},

			getItemSizePolicy : function(item) {
				var policy = this.callParent(arguments);
				if (typeof item.width == 'number') {
					policy = this.sizePolicies[policy.setsHeight ? 2 : 0];
				}
				return policy;
			},

			getPos : function(itemContext, info, dimension) {
				var size = itemContext.props[dimension]
						+ info.margins[dimension], pos = Math
						.round((info.targetSize[dimension] - size) / 2);

				return Math.max(pos, 0);
			},

			getSize : function(item, info, dimension) {
				var ratio = item[dimension];

				if (typeof ratio == 'string' && this.percentRe.test(ratio)) {
					ratio = parseFloat(ratio) / 100;
					return info.targetSize[dimension] * (ratio || 1)
							- info.margins[dimension];
				} else {
					// Not a ratio

					// ratio = item[dimension + 'Ratio']; // backwards compat
					return item[dimension];
				}

			},

			positionItemX : function(itemContext, info) {
				debugger;
				var left = this.getPos(itemContext, info, 'width');

				itemContext.setProp('x', left);
			},

			positionItemY : function(itemContext, info) {
				debugger;
				var top = this.getPos(itemContext, info, 'height');

				itemContext.setProp('y', top);
			},

			setItemHeight : function(itemContext, info) {
				debugger;
				var height = this.getSize(itemContext.target, info, 'height');

				itemContext.setHeight(height);
			},

			setItemWidth : function(itemContext, info) {
				debugger;
				var width = this.getSize(itemContext.target, info, 'width');

				itemContext.setWidth(width);
			}
		});
