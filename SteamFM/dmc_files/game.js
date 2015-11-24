"use strict";


function ShowEmbedWidget( )
{
	$J('#widget_create').show();
	$J('#widget_finished').hide();

	var $Content = $J('#EmbedModal');
	$Content.detach();
	$Content.show();

	var deferred = new jQuery.Deferred();
	var fnOK = function() { deferred.resolve(); };

	var Modal = _BuildDialog( "Criar widget para incorporar", $Content, [], fnOK, {} );
	deferred.always( function() { Modal.Dismiss(); } );
	Modal.Show();

	// attach the deferred's events to the modal
	deferred.promise( Modal );

	Modal.always(
		function() {
			// save it away again for later
			$Content.hide();
			$J(document.body).append( $Content );
		}
	);
}

function ShowShareDialog( )
{
	var $Content = $J('#ShareModal');
	$Content.detach();
	$Content.show();

	ShowAlertDialog( "Compartilhar", $Content).always(
		function() {
			// save it away again for later
			$Content.hide();
			$J(document.body).append( $Content );
		}
	);

}

function CreateWidget( nAppId )
{
	$J('#widget_create').hide();

	var nSubId = $J('input[name=w_subid]').val();
	if( !nSubId )
		nSubId = $J('input:radio[name=w_rsubid]:checked').val();

	var strDesc = $J('textarea[name=w_text]').val();

	var strWidgetURL;
	if( nSubId )
		strWidgetURL = 'http://store.steampowered.com/widget/' + nAppId + '/' + nSubId + '/';
	else
		strWidgetURL = 'http://store.steampowered.com/widget/' + nAppId + '/';

	if( strDesc )
		strWidgetURL = strWidgetURL + '?t=' + encodeURIComponent( strDesc );

	var strWidgetCode = '<iframe src="' + strWidgetURL + '" frameborder="0" width="646" height="190"></iframe>';

	var $iframe = $J(strWidgetCode);

	$J('#widget_container').empty();
	$J('#widget_container').append($iframe);
	$J('#widget_code').val( strWidgetCode );

	$J('#widget_finished').show();
}

function InitQueueControls( store_appid, steamworks_appid )
{
	var $FollowBtn = $J('.queue_control_button.queue_btn_follow .queue_btn_inactive');
	var $UnFollowBtn = $J('.queue_control_button.queue_btn_follow .queue_btn_active');
	var $IgnoreBtn = $J('.queue_control_button.queue_btn_ignore .queue_btn_inactive');
	var $UnIgnoreBtn = $J('.queue_control_button.queue_btn_ignore .queue_btn_active');

	$IgnoreBtn.click( function() {
		$J.post( 'http://store.steampowered.com/recommended/ignorerecommendation/', {
			sessionid: g_sessionID,
			appid: store_appid
		}).done( function() {
			$IgnoreBtn.hide();
			$UnIgnoreBtn.show();
			GDynamicStore.InvalidateCache();
		}).fail( function() {
			ShowAlertDialog( 'Dispensar', 'Houve um problema ao salvar as suas alterações. Por favor, tente novamente mais tarde.' );
		});
	});

	$UnIgnoreBtn.click( function() {
		$J.post( 'http://store.steampowered.com/recommended/ignorerecommendation/', {
			sessionid: g_sessionID,
			appid: store_appid,
			remove: 1
		}).done( function() {
			$IgnoreBtn.show();
			$UnIgnoreBtn.hide();
			GDynamicStore.InvalidateCache();
		}).fail( function() {
			ShowAlertDialog( 'Dispensar', 'Houve um problema ao salvar as suas alterações. Por favor, tente novamente mais tarde.' );
		});
	});

	$FollowBtn.click( function() {
		$J.post( 'http://store.steampowered.com/explore/followgame/', {
			sessionid: g_sessionID,
			appid: steamworks_appid
		}).done( function() {
			$FollowBtn.hide();
			$UnFollowBtn.show();
		}).fail( function() {
			ShowAlertDialog( 'Seguir', 'Houve um problema ao salvar as suas alterações. Por favor, tente novamente mais tarde.' );
		});
	});

	$UnFollowBtn.click( function() {
		$J.post( 'http://store.steampowered.com/explore/followgame/', {
			sessionid: g_sessionID,
			appid: steamworks_appid,
			unfollow: 1
		}).done( function() {
			$FollowBtn.show();
			$UnFollowBtn.hide();
		}).fail( function() {
			ShowAlertDialog( 'Seguir', 'Houve um problema ao salvar as suas alterações. Por favor, tente novamente mais tarde.' );
		});
	});
}


function InitAutocollapse()
{
	$J('.game_page_autocollapse').each( function() {
		var content = this;
		var $Content = $J(content);
		$Content.wrap( $J('<div/>', {'class': 'game_page_autocollapse_ctn' } ) );

		var $Container = $Content.parent();

		var $ReadMore = $J('<div/>', {'class': 'game_page_autocollapse_readmore' }).text( 'SAIBA MAIS' );
		var $Fade = $J('<div/>', {'class': 'game_page_autocollapse_fade' } ).append( $ReadMore );
		$Container.append( $Fade );

		var nInterval = 0;
		var nMaxHeight = parseInt( $Content.css('max-height') );
		var bMaxHeightSet = true;

		$Content.on( 'gamepage_autocollapse_expand', function() {
			if ( $Container.hasClass( 'collapsed' ) )
			{
				$Container.removeClass( 'collapsed' );
				$Container.addClass( 'expanded' );

				if ( bMaxHeightSet )
				{
					$Content.animate( {'max-height': content.scrollHeight + 20 + 'px'}, 'fast', null, function() { $Content.css('max-height', 'none' ); } );
				}
				window.clearInterval( nInterval );
			}
		});

		$ReadMore.click( function() { $Content.trigger('gamepage_autocollapse_expand'); } );

		var fnCheckHeight = function ()	{
			if ( content.scrollHeight > nMaxHeight + 30 )
			{
				$Content.css( 'max-height', nMaxHeight + 'px' );
				$Container.addClass( 'collapsed' );
				window.clearInterval( nInterval );
				bMaxHeightSet = true;
			}
			else if ( bMaxHeightSet )
			{
				$Content.css( 'max-height', 'none' );
				bMaxHeightSet = false;
			}
		};

		nInterval = window.setInterval( fnCheckHeight, 250 );
		fnCheckHeight();

	});
}

function RenderMoreLikeThisBlock( rgRecommendedAppIDs )
{
	if ( !rgRecommendedAppIDs || !rgRecommendedAppIDs.length > 0 || !$J('#recommended_block_content').length )
	{
		$J('#recommended_block').hide();
		return;
	}

	var rgRecommendationsToShow = [];
	var nCurScore = 0;
	for ( var i = 0; i < rgRecommendedAppIDs.length; i++ )
	{
		var unAppID = rgRecommendedAppIDs[i];
		if ( GDynamicStore.BIsAppIgnored( unAppID ) )
			continue;
		else if ( GDynamicStore.BIsAppOwned( unAppID ) )
			rgRecommendationsToShow.push( { score: 3 + nCurScore++, appid: unAppID } );
		else
			rgRecommendationsToShow.push( { score: nCurScore++, appid: unAppID } );
	}

	rgRecommendationsToShow.sort( function( a, b ) { return a.score - b.score; } );

	for ( var i = 0; i < rgRecommendationsToShow.length; i++ )
	{
		var unAppID = rgRecommendationsToShow[i].appid;
		var rgItemData = GStoreItemData.rgAppData[ unAppID ];
		if ( !rgItemData )
			continue;

		var params = {'class': 'small_cap',
				'data-ds-appid': unAppID,
				'href': GStoreItemData.GetAppURL( unAppID, 'recommended' )
		};

		var $CapCtn = $J('<a/>', params );
		GStoreItemData.BindHoverEvents( $CapCtn, unAppID, null );

		$CapCtn.append( $J('<img/>', {src: rgItemData.small_capsulev5, 'class': 'small_cap_img' } ) );
		$CapCtn.append( $J('<h4/>').html( rgItemData.name ) );
		$CapCtn.append( $J(rgItemData.discount_block).addClass( 'discount_block_inline') );

		$J('#recommended_block_content').append( $CapCtn );
	}
	GDynamicStore.DecorateDynamicItems( $J('#recommended_block_content') );
	$J('#recommended_block_content').append( $J('<div/>', { style: 'clear: left;' } ) );
	$J('#recommended_block_content').trigger('v_contentschanged');
}

function ShowEULA( elLink )
{
	var win = window.open( elLink.href,'eula','height=584,width=475,resize=yes,scrollbars=yes');
	win.focus();
}


// formerly user_reviews_store.js

var g_recommendationContents = [ 'friend', 'all', 'positive', 'negative', 'recent', 'funny' ];


function OnRecommendationVotedUp( recommendationid )
{
	for ( var i = 0; i < g_recommendationContents.length; ++i )
	{
		$J( '#RecommendationVoteUpBtn' + g_recommendationContents[i] + recommendationid ).addClass( 'btn_active' );
		$J( '#RecommendationVoteDownBtn' + g_recommendationContents[i] + recommendationid ).removeClass( "btn_active" );
	}
}

function OnRecommendationVotedDown( recommendationid )
{
	for ( var i = 0; i < g_recommendationContents.length; ++i )
	{
		$J( '#RecommendationVoteUpBtn' + g_recommendationContents[i] + recommendationid ).removeClass( 'btn_active' );
		$J( '#RecommendationVoteDownBtn' + g_recommendationContents[i] + recommendationid ).addClass( "btn_active" );
	}
}

function OnRecommendationVotedTag( recommendationid, tagID, bRateUp )
{
	for ( var i = 0; i < g_recommendationContents.length; ++i )
	{
		if ( bRateUp )
		{
			$J( '#RecommendationVoteTagBtn' + g_recommendationContents[i] + recommendationid + '_' + tagID ).addClass( 'btn_active' );
		}
		else
		{
			$J( '#RecommendationVoteTagBtn' + g_recommendationContents[i] + recommendationid + '_' + tagID ).removeClass( 'btn_active' );
		}
	}
}

function RequestCurrentUserRecommendationVotes( recommendationIDs )
{
	if ( recommendationIDs.length == 0 )
	{
		return;
	}

	$J.post( 'http://store.steampowered.com//userreviews/ajaxgetvotes/', {
			'recommendationids' : recommendationIDs
		}
	).done( function( response ) {
			if ( response.success == 1 )
			{
				var votes = response.votes;
				for ( var i = 0; i < votes.length; ++i )
				{
					var vote = votes[i];
					if ( vote.voted_up )
					{
						OnRecommendationVotedUp( vote.recommendationid );
					}
					else if ( vote.voted_down )
					{
						OnRecommendationVotedDown( vote.recommendationid );
					}
					if ( vote.voted_funny )
					{
						OnRecommendationVotedTag( vote.recommendationid, 1, true );
					}
				}
			}
		} );
}

function UserReviewVoteUp( id )
{
	UserReview_Rate( id, true, 'http://store.steampowered.com/',
		function( rgResults ) {
			OnRecommendationVotedUp( id );
		}
	);
}

function UserReviewVoteDown( id )
{
	UserReview_Rate( id, false, 'http://store.steampowered.com/',
		function( rgResults ) {
			OnRecommendationVotedDown( id );
		}
	);
}

function UserReviewVoteTag( id, tagID, elemID )
{
	var elem = $J( '#' + elemID );
	var bRateUp = !elem.hasClass( 'btn_active' );
	UserReview_VoteTag( id, tagID, bRateUp, 'http://store.steampowered.com/',
		function( rgResults ) {
			OnRecommendationVotedTag( id, tagID, bRateUp );
		}
	);
}

function UserReviewShowMore( id, context )
{
	$J('#ReviewContent'+context+id).parent().removeClass('partial');
	$J('#ReviewContent'+context+id).parent().addClass('expanded');
}

function LoadMoreReviews( appid, startOffset, dayRange, context, language )
{
	$J( "#ViewAllReviews" + context ).remove();
	$J( "#LoadMoreReviews" + context ).remove();
	$J( "#LoadingMoreReviews" + context ).show();

	var container = $J( "#Reviews_" + context );

	$J.get( 'http://store.steampowered.com//appreviews/' + appid,{
		'start_offset' : startOffset,
		'day_range' : dayRange,
		'filter' : context,
		'language' : language
	}).done( function( data ) {
		if ( data.success == 1 )
		{
			$J( "#LoadingMoreReviews" + context ).remove();

			// remove duplicates
			var recommendationIDs = [];
			var temp = $J('<div></div>');
			temp.append( data.html );
			for ( var i = 0; i < data.recommendationids.length; ++i )
			{
				var recommendationid = data.recommendationids[i];
				var elemID = "#ReviewContent" + context + recommendationid;
				if ( $J( elemID ).length != 0 )
				{
					temp.find( elemID ).parent().remove();
				}
				else
				{
					recommendationIDs.push( recommendationid );
				}
			}

			container.append( temp.children() );

			// all dupes, request more
			if ( data.recommendationids.length != 0 && recommendationIDs.length == 0 )
			{
				LoadMoreReviews(appid, startOffset + data.recommendationids.length, data.dayrange, context, language );
			}
			else
			{
				CollapseLongReviews();
				RequestCurrentUserRecommendationVotes( recommendationIDs );
			}
		}
	} );
}

function SelectReviews( appid, context, reviewDayRange, language )
{
	$J( "#ReviewsTab_all" ).removeClass( "active" );
	$J( "#ReviewsTab_recent" ).removeClass( "active" );
	$J( "#ReviewsTab_positive" ).removeClass( "active" );
	$J( "#ReviewsTab_negative" ).removeClass( "active" );
	$J( "#ReviewsTab_funny" ).removeClass( "active" );
	$J( "#ReviewsTab_" + context ).addClass( "active" );

	$J( "#Reviews_all" ).hide();
	$J( "#Reviews_recent" ).hide();
	$J( "#Reviews_positive" ).hide();
	$J( "#Reviews_negative" ).hide();
	$J( "#Reviews_funny" ).hide();
	$J( "#Reviews_" + context ).show();

	var container = $J( "#Reviews_" + context );
	if ( container.children().length == 0 )
	{
		LoadMoreReviews( appid, 0, reviewDayRange, context, language );
	}
}

function CollapseLongReviews()
{
	$J('.review_box').each( function(j, i){
		if( $J(i).outerHeight() > 400 )
		{
			if ( !$J(i).hasClass('expanded') )
			{
				$J(i).addClass('partial')
			}
		}
	});
}

//formerly app_reporting.js

var gReportedApp = false;
function ShowReportDialog( nAppId )
{
	if ( gReportedApp )
	{
		return;
	}

	var content = $J('<div/>', {'class': 'app_report_dialog' } );

	content.append( $J('<div/>', {'class': 'app_report_dialog_intro' } ).text('Por favor, escolha o motivo de estar denunciando este produto.') );

		var rgReportOptions = {"1":"Fraude \u2014 <span class=\"sub\">Este software tenta adquirir dados confidenciais, como as suas credenciais do Steam ou dados financeiros (como informa\u00e7\u00f5es de cart\u00e3o de cr\u00e9dito), de forma fraudulenta.<\/span>","2":"Prejudicial \u2014 <span class=\"sub\">Este software modifica o computador do usu\u00e1rio de formas inesperadas ou prejudiciais (ex: \u00e9 malware ou v\u00edrus)<\/span>","3":"Discursos de \u00f3dio \u2014 <span class=\"sub\">Cont\u00e9m discursos de \u00f3dio, isto \u00e9, discursos que promovem avers\u00f5es, viol\u00eancia ou discrimina\u00e7\u00e3o contra grupos ou pessoas com base na etnia, religi\u00e3o, g\u00eanero, idade, defici\u00eancia ou orienta\u00e7\u00e3o sexual<\/span>","4":"Pornografia \u2014 <span class=\"sub\">Cont\u00e9m pornografia<\/span>","5":"Conte\u00fado adulto \u2014 <span class=\"sub\">Cont\u00e9m conte\u00fado adulto que n\u00e3o est\u00e1 adequadamente classificado nem possui verifica\u00e7\u00e3o de idade<\/span>","6":"Difamat\u00f3rio \u2014 <span class=\"sub\">Cont\u00e9m declara\u00e7\u00f5es caluniosas ou difamat\u00f3rias<\/span>","7":"Ofensivo \u2014 <span class=\"sub\">Cont\u00e9m conte\u00fado claramente ofensivo ou que tem a inten\u00e7\u00e3o de chocar ou enojar espectadores<\/span>","8":"Explora\u00e7\u00e3o infantil \u2014 <span class=\"sub\">Cont\u00e9m conte\u00fado que explora crian\u00e7as de alguma forma<\/span>","11":"Viola\u00e7\u00e3o de termos legais \u2014 <span class=\"sub\">Cont\u00e9m conte\u00fado que viola leis na sua jurisdi\u00e7\u00e3o<\/span>","13":"N\u00e3o funcional \u2014 <span class=\"sub\">N\u00e3o baixa, inicia ou roda corretamente, mesmo em uma m\u00e1quina que atende aos requisitos m\u00ednimos.<\/span>"};
	var rgReportOptionElements = [];
	for ( var eReportType in rgReportOptions )
	{
		var $ReportOption = $J('<div/>', {'class': 'app_report_dialog_option' } );
		$ReportOption.append( $J('<div/>', {'class': 'app_report_dialog_option_input' }).append( $J('<input/>', {'type': 'radio', 'name':'report_type', 'value': eReportType, 'id': 'report_type_' + eReportType } ) ) );
		$ReportOption.append( $J('<div/>', {'class': 'app_report_dialog_option_text' }).append( $J('<label/>', {'for': 'report_type_' + eReportType }).html( rgReportOptions[eReportType] ) ) );
		rgReportOptionElements.push( $ReportOption );
	}

	for ( var j, x, i = rgReportOptionElements.length; i; j = parseInt(Math.random() * i), x = rgReportOptionElements[--i], rgReportOptionElements[i] = rgReportOptionElements[j], rgReportOptionElements[j] = x );
	for ( var i = 0; i < rgReportOptionElements.length; ++i )
	{
		content.append( rgReportOptionElements[i] );
	}

	var textArea = $J('<textarea/>', { 'class': 'app_report_dialog_reason', 'id' : 'ReportReason',  'name' : 'report_reason' } );
	var maxReasonLength = 512;
	textArea.attr( 'maxlength', maxReasonLength );
	textArea.bind( "keyup change",
		function()
		{
			var str = $J(this).val()
			var mx = parseInt($J(this).attr('maxlength'))
			if (str.length > mx)
			{
				$J(this).val(str.substr(0, mx))
				return false;
			}
		}
	);
	content.append( $J('<div/>', {'class': 'app_report_dialog_intro' } ).text('Aqui você pode inserir informações adicionais que considere relevante:') );
	content.append( textArea );

	content.append( $J('<div/>', {'class': 'app_report_dialog_dmca' } ).html('Caso deseje denunciar violação de direitos autorais e é o detentor destes, prossiga para o nosso formulário de notificação de violação de direitos autorais compatível com DMCA <a href="http://steamcommunity.com/dmca/create/">aqui</a>.') );

	var dialog = ShowConfirmDialog( 'Denunciar produto', content, 'Denunciar');

	dialog.done( function() {
		var eReportTypeSelected = content.find( 'input[type=radio]:checked' ).val();
		if ( eReportTypeSelected )
		{
			$J.post(
				'http://store.steampowered.com/appreport/' + nAppId + '/report/',
				{
					'report_type' : eReportTypeSelected,
					'report_reason' : content.find( 'textarea' ).val(),
					'sessionid': g_sessionID
				}
			).done( function( json ) {
					gReportedApp = true;
					$J( "#ReportAppBtn").addClass( 'btn_active' );
				}
			).fail( function( jqXHR ) {
					var json = jqXHR.responseText.evalJSON();
					if ( json.success == 29 )
					{
						ShowAlertDialog( 'Erro', 'Você já denunciou este produto!' );
						gReportedApp = true;
						$J( "#ReportAppBtn").addClass( 'btn_active' );
					}
					else
					{
						ShowAlertDialog( 'Erro', 'Houve um problema ao salvar a sua denúncia. Por favor, tente novamente mais tarde.' );
					}

				} );
		}
		else
		{
			ShowAlertDialog( 'Erro', 'Você precisa escolher um motivo para a denúncia do produto!' );
		}
	});
}

function ShowGotSteamModal( strSteamURL, strAppName, strPlayLaunchVerb )
{
		var $ModalContent = $J("<div class=\"gotsteamModal\">\r\n\t<div class=\"got_steam_ctn\">\r\n\t<div class=\"got_steam_box\">\r\n\t\t<h1>Voc\u00ea tem o Steam?<\/h1>\r\n\t\t<p>O <a href=\"http:\/\/store.steampowered.com\/about\/\">aplicativo do Steam para computadores<\/a> precisa estar instalado antes de instalar e iniciar <strong class=\"gotSteam_AppName\"><\/strong>. O Steam est\u00e1 instalado neste computador?<\/p>\r\n\t\t<div class=\"gotsteam_buttons\">\r\n\t\t\t<a class=\"gotSteam_SteamURL btn_blue leftbtn\" href=\"\">\r\n\t\t\t\t<h3>Sim, o Steam est\u00e1 instalado<\/h3>\r\n\t\t\t\t<h5 class=\"gotsteam_action\"><\/h5>\r\n\t\t\t<\/a>\r\n\t\t\t<a href=\"http:\/\/store.steampowered.com\/about\/\" class=\"btn_blue\">\r\n\t\t\t\t<h3>N\u00e3o, eu preciso do Steam<\/h3>\r\n\t\t\t\t<h5>Saiba mais sobre o Steam e baixe-o<\/h5>\r\n\t\t\t<\/a>\r\n\t\t\t<div style=\"clear: left;\"><\/div>\r\n\t\t<\/div>\r\n\t\t<div class=\"got_steam_low_block\">\r\n\t\t\t<div class=\"gotsteam_steam_ico\"><img src=\"http:\/\/store.akamai.steamstatic.com\/public\/images\/v6\/steam_ico.png\" width=\"40\" height=\"40\" border=\"0\" \/><\/div>\r\n\t\t\tO Steam \u00e9 a melhor plataforma de jogos para computador. O cadastro \u00e9 gratuito e \u00e9 f\u00e1cil de usar. <a href=\"http:\/\/store.steampowered.com\/about\/\">Saiba mais sobre o Steam.<\/a>\r\n\t\t<\/div><\/div>\r\n\t<\/div>\r\n<\/div>");
	$ModalContent.find('.gotSteam_AppName').text( strAppName );
	$ModalContent.find('.gotsteam_action').text( strPlayLaunchVerb );
	$ModalContent.find( '.gotSteam_SteamURL').attr( 'href', strSteamURL );
	var Modal = new CModal( $ModalContent );
	Modal.Show();
	//ShowDialog( 'Got Steam?', $ModalContent );
}


