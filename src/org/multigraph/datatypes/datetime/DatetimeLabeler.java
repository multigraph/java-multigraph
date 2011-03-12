package org.multigraph.datatypes.datetime;

import org.multigraph.*;
import org.multigraph.datatypes.DataValue;
import org.multigraph.datatypes.Formatter;
import org.multigraph.datatypes.Labeler;

public class DatetimeLabeler extends Labeler
{
    private DatetimeInterval mSpacing;
	private DatetimeValue    mStart;
    private TurboDate        mCurrentTurboDate;
    private DatetimeValue    mEnd;
    private double           mLabelWidthPixels;
    private double           mPixelsPerInchFactor;
    private double           mLastTextLabelWidth;
    private double           mLastTextLabelHeight;
    private double           mMSSpacing;
    private int              mStep;
    private TurboDate        mStartTurboDate;
    private TurboDate        mFirstTickTurboDate;


    public DatetimeLabeler(DatetimeInterval spacing,
                           Formatter formatter,
                           DatetimeValue start,
                           DPoint position,
                           double angle,
                           DPoint anchor) {
        super(formatter, position, angle, anchor);
        mSpacing             = spacing;
        mStart               = start;
        mCurrentTurboDate    = null;
        mEnd                 = null;
        mLabelWidthPixels    = 0;
        mPixelsPerInchFactor = 0.8 * (60.0 / 72.0);
        mLastTextLabelWidth  = 25;
        mLastTextLabelHeight = 25;
        mStep                = 0;
        mStartTurboDate      = new TurboDate(mStart.getDoubleValue());
        mMSSpacing           = spacing.getDoubleValue();
    }

	@Override
    public double getLabelDensity(Axis axis) {
        double absAngle          = Math.abs(mAngle) * 3.14156 / 180;
        double labelPixels       = (axis.getOrientation() == AxisOrientation.HORIZONTAL)
            ? mLastTextLabelHeight * Math.sin(absAngle) + mLastTextLabelWidth * Math.cos(absAngle)
            : mLastTextLabelHeight * Math.cos(absAngle) + mLastTextLabelWidth * Math.sin(absAngle);
        double spacingPixels     = mMSSpacing * Math.abs(axis.getAxisToDataRatio());
        double density           = labelPixels / spacingPixels;
        return density;
	}

	@Override
	public boolean hasNext() {
        return mCurrentTurboDate.getTimeInMillis() <= mEnd.getDoubleValue();
    }

	@Override
    public DataValue next() {
        DataValue val = new DatetimeValue(mCurrentTurboDate.getTimeInMillis());
        ++mStep;
        mCurrentTurboDate = mFirstTickTurboDate.clone();
        mCurrentTurboDate.add(mSpacing.getUnit(), mStep * mSpacing.getDoubleValue());
        return val;
    }		

    //@override 
    public DataValue peekNext() {
        return new DatetimeValue(mCurrentTurboDate.getTimeInMillis());
    }

	@Override
	public void prepare(DataValue min, DataValue max) {
        int direction = max.compareTo(min);
        TurboDate dataStartTurboDate = (direction > 0) ? new TurboDate(min.getDoubleValue()) : new TurboDate(max.getDoubleValue());
        mFirstTickTurboDate = dataStartTurboDate.firstTickAtOrAfter(mStartTurboDate, mSpacing.getDoubleValue(), mSpacing.getUnit());
        /*
        switch (mSpacing.getUnit()) {

        case YEAR:
            mFirstTickTurboDate = dataStartTurboDate.firstYearSpacingTickAtOrAfter(mStartTurboDate, mSpacing.getDoubleValue());
            break;

        case MONTH:
            mFirstTickTurboDate = dataStartTurboDate.firstMonthSpacingTickAtOrAfter(mStartTurboDate, mSpacing.getDoubleValue());
            break;

        case DAY:
            mFirstTickTurboDate = dataStartTurboDate.firstDaySpacingTickAtOrAfter(mStartTurboDate, mSpacing.getDoubleValue());
            break;

        case HOUR:
            mFirstTickTurboDate = dataStartTurboDate.firstHourSpacingTickAtOrAfter(mStartTurboDate, mSpacing.getDoubleValue());
            break;

        case MINUTE:
            mFirstTickTurboDate = dataStartTurboDate.firstMinuteSpacingTickAtOrAfter(mStartTurboDate, mSpacing.getDoubleValue());
            break;

        case SECOND:
            mFirstTickTurboDate = dataStartTurboDate.firstSecondSpacingTickAtOrAfter(mStartTurboDate, mSpacing.getDoubleValue());
            break;

        case MILLISECOND:
        default:
            mFirstTickTurboDate = dataStartTurboDate.firstMillisecondSpacingTickAtOrAfter(mStartTurboDate, mSpacing.getDoubleValue());
            break;
        }
        */
        mCurrentTurboDate = mFirstTickTurboDate.clone();
        mEnd = (DatetimeValue)((direction > 0) ? max : min);
        mStep = 0;
	}

	@Override
	public void renderLabel(GraphicsContext g, Axis axis, DataValue value) {
        double a = axis.dataValueToAxisValue(value);
        double baseX, baseY;
        if(axis.getOrientation() == AxisOrientation.VERTICAL) {
            baseX = axis.getPerpOffset() + mPosition.getX();
            baseY = a + mPosition.getY();
        } else {
            baseX = a + mPosition.getX();
            baseY = axis.getPerpOffset() + mPosition.getY();
        }
        String string = mFormatter.format(value);
        g.drawString(string,
                     baseX, baseY,
                     mAnchor.getX(),   mAnchor.getY(),
                     mPosition.getX(), mPosition.getY(),
                     mAngle * Math.PI / 180.0);
        Box bounds = g.getStringBounds(string);
        mLastTextLabelWidth  = bounds.getWidth();
        mLastTextLabelHeight = bounds.getHeight();
	}
//
//    private var _startUTCms:Number;
//    private var _startTurboDate:TurboDate;
//    private var _firstTickTurboDate:TurboDate;
//    private var _currentUTCms:Number;
//    private var _currentTurboDate:TurboDate;
//    private var _end:Number;
//    private var _formatter:DateFormatter;
//    private var _step:int;
//    
//    private var _msSpacing:Number;
//    private var _spacingPixels:Number;
//    private var _labelWidthPixels:Number;
//    private var _pixelsPerInchFactor:Number;
//    
//    private var _lastTextLabelWidth:Number = 25;
//    private var _lastTextLabelHeight:Number = 25;
//    
//    public function DateLabeler(spacing:Number, unit:String, formatString:String, start:String, 
//                                px:Number, py:Number, angle:Number, ax:Number, ay:Number, textFormat:TextFormat, boldTextFormat:TextFormat)
//    {
//      _formatter 			 = new DateFormatter(formatString);
//      _startUTCms            = _formatter.parse(start);
//      _startTurboDate        = new TurboDate(_startUTCms);
//      super(spacing, unit, formatString, _startUTCms, px, py, angle, ax, ay, textFormat, boldTextFormat);
//      _currentUTCms 		 = null;
//      _end 				     = null;
//      _msSpacing 			 = null;
//      _spacingPixels 		 = 0;
//      _labelWidthPixels 	 = 0;
//      _pixelsPerInchFactor = 0.8 * (60.0 / 72.0);
//      _unit                  = unit;
//      
//      switch(unit){
//      case "H":
//        _msSpacing = _spacing * 3600000;
//        break;
//      case "D":
//        _msSpacing = _spacing * 3600000 * 24;
//        break;
//      case "M":
//        _msSpacing = _spacing * 3600000 * 24 * 30;
//        break;
//      case "Y":
//        _msSpacing = _spacing * 3600000 * 24 * 365;
//        break;
//      case "m":
//        _msSpacing = _spacing * 60000;
//        break;
//      default:
//        _msSpacing = _spacing;
//        break;
//      }
//
//    }
//    
//    override public function labelDensity(axis:Axis):Number {
//      /*
//        var labelLength       = _formatter.getLength();
//        var labelHeightPixels = _fontSize * _pixelsPerInchFactor;
//        var labelWidthPixels  = labelLength * labelHeightPixels;
//        var absAngle          = Math.abs(_angle) * 3.14156 / 180;
//        var labelPixels       = (axis.orientation == Axis.ORIENTATION_HORIZONTAL)
//        ? labelHeightPixels * Math.sin(absAngle) + labelWidthPixels * Math.cos(absAngle)
//        : labelHeightPixels * Math.cos(absAngle) + labelWidthPixels * Math.sin(absAngle);
//      */
//      var absAngle:Number          = Math.abs(_angle) * 3.14156 / 180;
//      var labelPixels:Number       = (axis.orientation == Axis.ORIENTATION_HORIZONTAL)
//        ? _lastTextLabelHeight * Math.sin(absAngle) + _lastTextLabelWidth * Math.cos(absAngle)
//        : _lastTextLabelHeight * Math.cos(absAngle) + _lastTextLabelWidth * Math.sin(absAngle);
//      var spacingPixels:Number     = _msSpacing * Math.abs(axis.axisToDataRatio);
//      var density:Number           = labelPixels / spacingPixels;
//      
//      return density;
//    }
//    
//    override public function renderLabel(sprite:MultigraphUIComponent, axis:Axis, value:Number):void {
//      var a:Number  = axis.dataValueToAxisValue(value);
//      
//      var px:Number, py:Number;
//      if(axis.orientation == Axis.ORIENTATION_VERTICAL) {
//        px = axis.perpOffset + _px;
//        py = a + _py;
//      } else {
//        px = a + _px;
//        py = axis.perpOffset + _py;
//      }
//      var tLabel:TextLabel = new TextLabel(_formatter.format(value),
//                                           _useBold ? _boldTextFormat :_textFormat,
//                                           px, py,
//                                           _ax, _ay,
//                                           _angle);
//      sprite.addChild(tLabel);
//      _lastTextLabelWidth  = tLabel.textWidth;   	
//      _lastTextLabelHeight = tLabel.textHeight;   	
//    }
//    
//    override public function prepare(dataMin:Number, dataMax:Number):void {
//      var direction:int = (dataMax >= dataMin) ? 1 : -1;
//      var dataStartTurboDate:TurboDate = (direction > 0) ? new TurboDate(dataMin) : new TurboDate(dataMax);
//      switch (_unit) {
//      case "m":
//        _firstTickTurboDate = dataStartTurboDate.firstMinuteSpacingTickAtOrAfter(_startTurboDate, _spacing);
//        _msSpacing = _spacing * TurboDate.millisecondsInOneMinute;
//        break;
//      case "H":
//        _firstTickTurboDate = dataStartTurboDate.firstHourSpacingTickAtOrAfter(_startTurboDate, _spacing);
//        _msSpacing = _spacing * TurboDate.millisecondsInOneHour;
//        break;
//      case "D":
//        _firstTickTurboDate = dataStartTurboDate.firstDaySpacingTickAtOrAfter(_startTurboDate, _spacing);
//        _msSpacing = _spacing * TurboDate.millisecondsInOneDay;
//        break;
//      case "M":
//        _firstTickTurboDate = dataStartTurboDate.firstMonthSpacingTickAtOrAfter(_startTurboDate, _spacing);
//        _msSpacing = _spacing * TurboDate.millisecondsInOneDay * 30;
//        break;
//      case "Y":
//        _firstTickTurboDate = dataStartTurboDate.firstYearSpacingTickAtOrAfter(_startTurboDate, _spacing);
//        _msSpacing = _spacing * TurboDate.millisecondsInOneDay * 365;
//        break;
//      default:
//        _firstTickTurboDate = dataStartTurboDate.firstMillisecondSpacingTickAtOrAfter(_startTurboDate, _spacing);
//        _msSpacing = _spacing;
//        break;
//      }
//      _currentTurboDate = _firstTickTurboDate.clone();
//      _currentUTCms = _currentTurboDate.getUTCMilliseconds();
//      _end = (direction > 0) ? dataMax : dataMin;
//      _step = 0;
//    }
//    
//    override public function hasNext():Boolean {
//      return _currentUTCms <= _end;
//    }
//    
//    override public function next():Number {
//      var val:Number = _currentUTCms;
//      ++_step;
//      _currentTurboDate = _firstTickTurboDate.clone();
//      switch (_unit) {
//      case "m":
//        _currentTurboDate.addMinutes(_step * _spacing);
//        break;
//      case "H":
//        _currentTurboDate.addHours(_step * _spacing);
//        break;
//      case "D":
//        _currentTurboDate.addDays(_step * _spacing);
//        break;
//      case "M":
//        _currentTurboDate.addMonths(_step * _spacing);
//        break;
//      case "Y":
//        _currentTurboDate.addYears(_step * _spacing);
//        break;
//      default:
//        _currentTurboDate.addMilliseconds(_step * _spacing);
//        break;
//      }
//      _currentUTCms = _currentTurboDate.getUTCMilliseconds();
//      return val;
//    }		
//  }
}
