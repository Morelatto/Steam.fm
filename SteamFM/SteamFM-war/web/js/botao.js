function Button( $element ) {
  this.$element   = $element;
  this.timeout_id = null;
  
  this.$element.on('click.button', $.proxy(this.on_click, this));
}

Button.prototype.on_click = function(){
  this.$element.addClass('loading');
  
  if ( this.timeout_id === null ) {
    this.timeout_id = setTimeout($.proxy(this.remove_loading, this), 5000);
  }
};

Button.prototype.remove_loading = function(){
  this.$element.removeClass('loading');
  clearTimeout( this.timeout_id );
  this.timeout_id = null;
};

new Button( $('.button') );